package com.course.calypso.service;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Builder
@Data
@NoArgsConstructor
public class FoodProcessorTask {

    private String[] item;

    public FoodProcessorTask(String[] item){
        this.item = item;
    }

    public void process(){
        log.info("Process food :{}",this.item[0]);
    }
}
