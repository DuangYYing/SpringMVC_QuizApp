package com.bfs.springmvcdemo.domain;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    private int category_id;
    private String name;
    private String image_url;

}
