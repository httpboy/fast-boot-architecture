package com.fast.email;

import lombok.Data;


/**
 * @Classname EmailDto
 * @Created by whf
 */
@Data
public class EmailDto {

    private String email;

    private String title;

    private String content;

    private String filePath;
}
