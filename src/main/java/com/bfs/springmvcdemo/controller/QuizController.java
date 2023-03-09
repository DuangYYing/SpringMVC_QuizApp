package com.bfs.springmvcdemo.controller;

import com.bfs.springmvcdemo.domain.*;
import com.bfs.springmvcdemo.service.QuizService;
import com.bfs.springmvcdemo.service.UserService;
import com.mysql.cj.protocol.x.Notice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class QuizController {

    private QuizService quizService;
    private UserService userService;

    @Autowired
    public QuizController(QuizService quizService,UserService userService ) {

        this.quizService = quizService;
        this.userService = userService;
    }
    @GetMapping("/homepage")
    public String getHomepage(Model model, HttpSession session) {
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/login";
        }
        List<Category> categories = quizService.getCategories();
        User user = (User) session.getAttribute("user");
        int user_Id = user.getId();
        List<Quiz> quizs = quizService.getAllQuizByUserId(user_Id);
        model.addAttribute("categories", categories);
        model.addAttribute("quizs", quizs);
        return "homepage";
    }


    @GetMapping("/quiz")
    public String getQuiz(Model model) {
        return "quiz";
    }
    @GetMapping("/quiz/{categoryId}")
    public String getQuizByCategory(@PathVariable("categoryId") int category_Id, Model model, HttpSession session) {
//         redirect to /quiz if user is already logged in
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/login";
        }

        List<Question> questions = quizService.getFiveQuestionsByCategory(category_Id);
        for(Question q: questions){
            List<Choice> choices = quizService.getAllChoicesForQuestion(q.getQuestion_id());
            q.setChoices(choices);
        }
        long startTime = System.currentTimeMillis();
        model.addAttribute("time_start", startTime);
        model.addAttribute("questions", questions);
        model.addAttribute("categoryId", category_Id);
        session.setAttribute("categoryId",category_Id);
        session.setAttribute("time_start",quizService.getDate(startTime));
        session.setAttribute("questions",questions);
        return "quiz";
    }
    @PostMapping("/quiz/{categoryId}")
    public String submitQuiz(
            @RequestParam(name = "selectedChoiceId0") Integer selectedChoiceId0,
            @RequestParam(name = "selectedChoiceId1") Integer selectedChoiceId1,
            @RequestParam(name = "selectedChoiceId2") Integer selectedChoiceId2,
            @RequestParam(name = "selectedChoiceId3") Integer selectedChoiceId3,
            @RequestParam(name = "selectedChoiceId4") Integer selectedChoiceId4,
            Model model,
            HttpSession session
    ) {
        long endTime = System.currentTimeMillis();
        model.addAttribute("time_end", quizService.getDate(endTime));
        User user = (User) session.getAttribute("user");
        int user_Id = user.getId();
        int category_Id = (Integer) session.getAttribute("categoryId");
        String category_name = quizService.getCategoryById(category_Id).getName();
        int  quizId = quizService.getLatestQuizId()+1;
        List<Integer> selectedChoiceIds = new ArrayList<>();
        selectedChoiceIds.add(selectedChoiceId0);
        selectedChoiceIds.add(selectedChoiceId1);
        selectedChoiceIds.add(selectedChoiceId2);
        selectedChoiceIds.add(selectedChoiceId3);
        selectedChoiceIds.add(selectedChoiceId4);
        List<QuizQuestion> quizQuestions = new ArrayList<>();
        int score =0;
        for(int i=0;i<selectedChoiceIds.size();i++){
            int selectedChoiceId = selectedChoiceIds.get(i);
            Optional<Choice> selectedChoice = quizService.getChoiceById(selectedChoiceId);
            QuizQuestion new_QQ = new QuizQuestion(quizId,selectedChoice.get().getQuestion_id(),selectedChoice.get().getChoice_id(),i,selectedChoice.get().isCorrect());
            quizService.addQuizQuestion(new_QQ);
            score+=selectedChoice.get().isCorrect()?1:0;
            quizQuestions.add(new_QQ);
        }
        Quiz newQuiz =new Quiz(quizId,user_Id, category_Id,category_name,session.getAttribute("time_start").toString(),model.getAttribute("time_end").toString(),score);
        quizService.addQuiz(newQuiz);
        return "redirect:/quiz-final-result/"+quizId;
    }

    @GetMapping("/quiz-final-result/{quizId}")
    public String getQuizResult(@PathVariable("quizId") int quizId,
            Model model, HttpSession session) {
        Quiz quiz = quizService.getQuizById(quizId);
        User quizUser = userService.getUserById(quiz.getUser_id());
        List<QuizQuestion> quizQuestions= quizService.getAllQuizQuestionByQuizId(quizId);
        List<Question> questionsOfQuiz = new ArrayList<>();
        List<Choice> selectedChoices = new ArrayList<>();
        List<Choice> correctChoices = new ArrayList<>();
        for(QuizQuestion qq : quizQuestions){
            Question q = quizService.getQuestionById(qq.getQuestion_id()).get();
            List<Choice> choices = quizService.getAllChoicesForQuestion(q.getQuestion_id());
            for(Choice c:choices){
                if(c.isCorrect()){correctChoices.add(c);}
            }
            q.setChoices(choices);
            questionsOfQuiz.add(q);
            Choice selectChoice = quizService.getChoiceById(qq.getUser_choice_id()).get();
            selectedChoices.add(selectChoice);

        }

        model.addAttribute("quiz",quiz);
        model.addAttribute("quizUser",quizUser);
        model.addAttribute("questionsOfQuiz",questionsOfQuiz);
        model.addAttribute("selectedChoices",selectedChoices);
        model.addAttribute("correctChoices",correctChoices);
        model.addAttribute("score",quiz.getScore());

        return "/quiz-final-result";
    }

    @GetMapping("/usersquizresult")
    public String getUsersQuizResult(Model model, HttpSession session,
                                     @RequestParam(name = "categoryFilter", defaultValue = "all") String categoryId,
                                     @RequestParam(name = "userFilter", defaultValue = "all") String userId){
        User users = (User) session.getAttribute("user");
        if(!users.isAdmin()){
            return "index";
        }
        List<Quiz> quizs = quizService.getQuizsFilterByUserCategroy(categoryId,userId);
        List<QuizResult> quizResults = new ArrayList<>();
        List<Category> allCategories = quizService.getCategories();
        for(Quiz q : quizs){
            String category = quizService.getCategoryById(q.getCategory_id()).getName();
            User user = userService.getUserById(q.getUser_id());
            String userFullName = user.getFirstname()+user.getLastname();
            List<QuizQuestion> quizQuestions= quizService.getAllQuizQuestionByQuizId(q.getQuiz_id());
            int numOfQuestions = quizQuestions.size();
            quizResults.add(new QuizResult(q.getQuiz_id(),q.getTime_end(),category,userFullName,numOfQuestions,q.getScore()));

        }
        List<Integer> allUserIds = quizService.getAllUserIds();
        model.addAttribute("allUserIds",allUserIds);
        model.addAttribute("quizs",quizs);
        model.addAttribute("quizResults",quizResults);
        model.addAttribute("allCategories",allCategories);

        return "/usersquizresult";
    }


    @GetMapping("/quizmanage")
    public String manageQuizQuestions(Model model, HttpSession session) {
        List<Question> allQuestions = quizService.getAllQuestions();
        List<Category> categories = quizService.getCategories();
        User user = (User) session.getAttribute("user");
        if(!user.isAdmin()){
            return "index";
        }
        model.addAttribute("categories", categories);
        for(Question q: allQuestions){
            List<Choice> choices = quizService.getAllChoicesForQuestion(q.getQuestion_id());
            q.setChoices(choices);
        }
        model.addAttribute("allQuestions", allQuestions);
        session.setAttribute("allQuestions", allQuestions);
        return "/quizmanage";
    }

    @GetMapping("/disablequestion")
    public String getDisableQuestion(Model model, HttpSession session) {
        List<Question> allQuestions = quizService.getAllQuestions();
        List<Category> categories = quizService.getCategories();
        User user = (User) session.getAttribute("user");
        if(!user.isAdmin()){
            return "index";
        }
        model.addAttribute("categories", categories);
        for(Question q: allQuestions){
            List<Choice> choices = quizService.getAllChoicesForQuestion(q.getQuestion_id());
            q.setChoices(choices);
        }
        model.addAttribute("allQuestions", allQuestions);
        session.setAttribute("allQuestions", allQuestions);
        return "/disablequestion";
    }
    @PostMapping("disablequestion")
    public String disableQuestion(
            @RequestParam(name = "questionId") Integer questionId,
            @RequestParam(name = "status") String status
    ){
        quizService.disableQuestion(questionId, status);
        return "redirect:/disablequestion";

    }
    @GetMapping("/updatequestion")
    public String getUpdateQuestion(Model model, HttpSession session) {
        List<Question> allQuestions = quizService.getAllQuestions();
        List<Category> categories = quizService.getCategories();
        User user = (User) session.getAttribute("user");
        if(!user.isAdmin()){
            return "index";
        }
        model.addAttribute("categories", categories);
        for(Question q: allQuestions){
            List<Choice> choices = quizService.getAllChoicesForQuestion(q.getQuestion_id());
            q.setChoices(choices);
        }
        model.addAttribute("allQuestions", allQuestions);
        session.setAttribute("allQuestions", allQuestions);
        return "/updatequestion";
    }
    @PostMapping("/updatequestion")
    public String updateQuestion(HttpServletRequest request,Model model,HttpSession session

    ){
        int questionId = Integer.parseInt(request.getParameter("questionId"));
        String questionText = request.getParameter("questionText");
        quizService.updateQuestion(questionId,questionText);
        for (int i = 1; i <= 5; i++) {
            String choice_description = request.getParameter("choice" + i);
            int choice_id = Integer.parseInt(request.getParameter("choiceId" + i));
            int question_id = quizService.getChoiceById(choice_id).get().getQuestion_id();
            boolean isCorrect = request.getParameter("choiceCorrect" + i).equals("true")?true:false;
            Choice choice = new Choice(choice_id,question_id,choice_description,isCorrect);
            quizService.updateChoice(choice);

        }
        return "redirect:/updatequestion";

    }

    @GetMapping("/addquestion")
    public String getAddQuestion(Model model, HttpSession session) {
        List<Question> allQuestions = quizService.getAllQuestions();
        List<Category> categories = quizService.getCategories();
        model.addAttribute("categories", categories);
        User user = (User) session.getAttribute("user");
        if(!user.isAdmin()){
            return "index";
        }
        for(Question q: allQuestions){
            List<Choice> choices = quizService.getAllChoicesForQuestion(q.getQuestion_id());
            q.setChoices(choices);
        }
        model.addAttribute("allQuestions", allQuestions);
        session.setAttribute("allQuestions", allQuestions);
        return "/addquestion";
    }
    @PostMapping("/addquestion")
    public String addQuestion(HttpServletRequest request,Model model,HttpSession session,
                              @RequestParam(name = "question_description") String question_description,
                              @RequestParam(name = "categories") String category_id,
                              @RequestParam(name = "choice1_description") String choice1_description,
                              @RequestParam(name = "choice2_description") String choice2_description,
                              @RequestParam(name = "choice3_description") String choice3_description,
                              @RequestParam(name = "choice4_description") String choice4_description,
                              @RequestParam(name = "choice5_description") String choice5_description,
                              @RequestParam(name = "correctAnswer_idx") String correctAnswer_idx

    ){

        Question newQuestion = new Question(Integer.parseInt(category_id),question_description);
        quizService.addQuestion(newQuestion);
        int questionId = quizService.getLatestQuestionId();
        List<String> choice_scriptions = new ArrayList<>();
        choice_scriptions.add(choice1_description);
        choice_scriptions.add(choice2_description);
        choice_scriptions.add(choice3_description);
        choice_scriptions.add(choice4_description);
        choice_scriptions.add(choice5_description);
        for(int i=1;i<=5;i++){
            boolean correct = false;
            if(i==Integer.parseInt(correctAnswer_idx)){
                correct = true;
            }
            Choice choice = new Choice(questionId,choice_scriptions.get(i-1),correct);
            quizService.addChoice(choice);

        }

        return "redirect:/addquestion";

    }






}
