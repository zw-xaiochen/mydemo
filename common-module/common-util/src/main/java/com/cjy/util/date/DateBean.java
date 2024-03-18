package com.cjy.util.date;

import lombok.Data;

import java.io.Serializable;


/**
 * @author Administrator
 */
@Data
public class DateBean implements Serializable {
    private Long startTime;

    private Long endTime;
}
