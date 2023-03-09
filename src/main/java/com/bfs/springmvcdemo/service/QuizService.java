package com.bfs.springmvcdemo.service;

import com.bfs.springmvcdemo.dao.*;
import com.bfs.springmvcdemo.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Random;
import java.util.stream.Collectors;

@Service
public class QuizService {

    private final QuizDAO quizDAO;
    private final QuestionDAO questionDAO;
    private final CategoryDAO categoryDAO;
    private final ChoiceDAO choiceDAO;

    private final QuizQuestionDAO quizQuestionDAO;
    Random random = new Random();
    @Autowired
    public QuizService(QuizDAO quizDAO, QuestionDAO questionDAO, CategoryDAO categoryDAO, ChoiceDAO choiceDAO, QuizQuestionDAO quizQuestionDAO) {
        this.quizDAO = quizDAO;
        this.questionDAO = questionDAO;
        this.categoryDAO = categoryDAO;
        this.choiceDAO = choiceDAO;
        this.quizQuestionDAO = quizQuestionDAO;
    }

    public List<Quiz> getAllQuizByUserId(int user_id) {
        return quizDAO.getAllQuizsByUserId(user_id);

    }

    public List<Quiz> getAllQuizs() {
        return quizDAO.getAllQuizs();

    }

    public List<Quiz> getQuizsFilterByUserCategroy(String categoryId,String useId) {
        if(categoryId.equals("all") && useId.equals("all")){
            return quizDAO.getAllQuizs().stream()
                    .sorted((a,b)->(b.getTime_end().compareTo(a.getTime_end())))
                    .collect(Collectors.toList());
        }else if(!categoryId.equals("all") && useId.equals("all")){
            return quizDAO.getAllQuizs().stream()
                    .filter(a->a.getCategory_id()==Integer.parseInt(categoryId))
                    .sorted((a,b)->(b.getTime_end().compareTo(a.getTime_end())))
                    .collect(Collectors.toList());
        }else if(categoryId.equals("all") && !useId.equals("all")){
            return quizDAO.getAllQuizs().stream()
                    .filter(a->a.getUser_id()==Integer.parseInt(useId))
                    .sorted((a,b)->(b.getTime_end().compareTo(a.getTime_end())))
                    .collect(Collectors.toList());
        }else{
            return quizDAO.getAllQuizs().stream()
                    .filter(a->a.getUser_id()==Integer.parseInt(useId) && a.getCategory_id()==Integer.parseInt(categoryId))
                    .sorted((a,b)->(b.getTime_end().compareTo(a.getTime_end())))
                    .collect(Collectors.toList());
        }


    }

    public List<Integer> getAllUserIds(){
        return quizDAO.getAllQuizs().stream()
                .map(quiz -> quiz.getUser_id())
                .distinct()
                .collect(Collectors.toList());
    }

    public Quiz getQuizById(int id) {
        return quizDAO.getAllQuizs().stream()
                .filter(a->a.getQuiz_id()==id)
                .findFirst()
                .orElse(new Quiz(-1,-1,-1,"invalid name","invalid_time_start","invalid_time_end",-1));
    }

    public int getLatestQuizId(){
        return quizDAO.getLatestQuiz().getQuiz_id();
    }

    public List<Question> getAllQuestionsByCategory(int category_id) {
        return questionDAO.getQuestionsByCategory(category_id);
    }

    public List<Question> getFiveQuestionsByCategory(int category_id) {
        return questionDAO.getQuestionsByCategory(category_id)
                .stream().sorted((a, b) -> random.nextInt(3) - 1)
                .filter(a->a.isActive()==true)
                .limit(5)
                .collect(Collectors.toList());
    }

    public List<Category> getCategories(){
        return categoryDAO.getAllCategories();
    }

    public Category getCategoryById(int categoryId){
        return categoryDAO.getCategoryById(categoryId);
    }

    public List<Choice> getAllChoicesForQuestion(int questionId){
        return choiceDAO.getAllChoicesForQuestion(questionId);
    }

    public Optional<Choice> getChoiceById(int choiceId){
        return choiceDAO.getChoiceById(choiceId);

    }

    public String getDate(long currentTimeInMillis){
        Date date = new Date(currentTimeInMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String formattedDate = dateFormat.format(date);
        return formattedDate;
    }

    public void addQuiz(Quiz quiz){
        quizDAO.addQuiz(quiz);
    }

    public void addQuizQuestion(QuizQuestion quizQuestion){
        quizQuestionDAO.addQuizQuestion(quizQuestion);
    }

    public List<QuizQuestion> getAllQuizQuestionByQuizId(int quizId){
        return quizQuestionDAO.getAllQuizQuestions()
                .stream().filter(a->a.getQuiz_id()==quizId)
                .limit(5)
                .collect(Collectors.toList());
    }

    public Optional<Question> getQuestionById(int questionId){
        return questionDAO.getQuestionById(questionId);
    }

    public String checkAnswer(Choice selectedChoice) {
        return selectedChoice.isCorrect() ? "Correct!" : "Incorrect";
    }

    public List<Question> getAllQuestions(){
        return questionDAO.getAllQuestions();
    }

    public void disableQuestion(int questionId, String status){
        questionDAO.disableQuestion(questionId,status);

    }

    public void updateQuestion(int question_id,String description){
        questionDAO.updateQuestion(question_id,description);
    }

    public void updateChoice(Choice choice){
        choiceDAO.updateChoice(choice);
    }

    public void addQuestion(Question question){
        questionDAO.addQuestion(question);
    }

    public int getLatestQuestionId(){
        return questionDAO.getLatestQuestion().getQuestion_id();
    }

    public void addChoice(Choice choice){
        choiceDAO.addChoice(choice);
    }







//    public List<Question> getQuestionsByQuizId(int quizId) {
//        return quizDAO.getQuizById(quizId);
//    }
//
//    public int saveQuizResult(QuizResult quizResult) {
//        return quizRepository.saveQuizResult(quizResult);
//    }
//
//    public List<QuizResult> getQuizResultsByUserId(int userId) {
//        return quizRepository.findQuizResultsByUserId(userId);
//    }
}
