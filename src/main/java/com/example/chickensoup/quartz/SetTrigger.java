//package com.example.chickensoup.quartz;
//
//import cn.edu.zucc.echo.entity.EchoQuestionnaireEntity;
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//@Service
//public class SetTrigger {
//    public  void setTrack(EchoQuestionnaireEntity questionnaire) throws SchedulerException, InterruptedException {
//        // 1、创建调度器Scheduler
//        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
//        Scheduler scheduler = schedulerFactory.getScheduler();
//        // 2、创建JobDetail实例，并与excute类绑定(Job执行内容)
//        JobDetail jobDetail = JobBuilder.newJob(RemindUndone.class)
//                .usingJobData("questionnaireId", questionnaire.getId())
//                .withIdentity("undoneTrack" + questionnaire.getId(), "Track").build();
//        // 3、构建Trigger实例,每隔1s执行一次
//        Date startDate = new Date();
//        startDate.setTime(startDate.getTime() + 5000);
//
//        Date endDate = new Date();
//        endDate.setTime(questionnaire.getDeadLine().toEpochMilli());
//
//        Trigger simpleTrigger = TriggerBuilder.newTrigger().withIdentity("undoneTrack" + questionnaire.getId(), "Track")
//                .usingJobData("trigger1", "这是jobDetail1的trigger")
//                .startNow()//立即生效
//                .startAt(startDate)
//                .endAt(endDate)
//                .withSchedule(SimpleScheduleBuilder.repeatHourlyForever(2))
//                .build();
//
//        //4、执行
//        scheduler.scheduleJob(jobDetail, simpleTrigger);
//        scheduler.start();
//
//
//    }
//}
