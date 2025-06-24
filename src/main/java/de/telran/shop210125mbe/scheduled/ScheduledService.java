package de.telran.shop210125mbe.scheduled;

import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ScheduledService {

    //@Scheduled(fixedDelay = 5000) //старт отсчета времени начинается после окончания работы метода
    public void scheduledTaskFixedDelay() throws InterruptedException {
        log.info("-- выполняется логика задания fixedDelay --");
        Thread.sleep(10000); //имитируем нагруженный процесс
    }

    @Async //метод будет запускаться в отдельном потоке
    //@Scheduled(fixedRate = 5000) //старт отсчета времени начинается c времени старта метода
    public void scheduledTaskFixedRate() throws InterruptedException {
        log.info("-- выполняется логика задания fixedRate -- "+Thread.currentThread().getName());
        Thread.sleep(10000); //имитируем нагруженный процесс
    }

    //задержка перед стартом 5 сек.
    //@Scheduled(initialDelay = 5000, fixedDelay = 2000) //старт отсчета времени начинается после окончания работы метода
    public void scheduledTaskFixedDelayWithInitialDelay() throws InterruptedException {
        log.info("-- выполняется логика задания fixedDelay c initialDelay --");
        Thread.sleep(5000); //имитируем нагруженный процесс
    }

    //"PT02H" - каждые 2 часа
    //@Scheduled(fixedDelayString = "PT03S") //старт отсчета времени начинается после окончания работы метода
    public void scheduledTaskFixedDelayIso() throws InterruptedException {
        log.info("-- выполняется логика задания fixedDelay время в ISO--");
        Thread.sleep(10000); //имитируем нагруженный процесс
    }

    //задаем интервал времени через настройки (application.properties)
  //  @Scheduled(fixedDelayString = "${test.fixed.delayed.iso}") //старт отсчета времени начинается после окончания работы метода
    @Scheduled(fixedDelayString = "${test.fixed.delayed}") //старт отсчета времени начинается после окончания работы метода
    @SchedulerLock(name="scheduledTaskFixedDelayIsoProperties",lockAtMostFor = "15m")
    public void scheduledTaskFixedDelayIsoProperties() throws InterruptedException {
        log.info("-- выполняется логика задания fixedDelay время в ISO время и настроек--");
        Thread.sleep(10000); //имитируем нагруженный процесс
    }

    //@Scheduled(cron = "0 15 10 * * *") //каждый день в 10:15
    //@Scheduled(cron = "${cron.expression}") //каждую минуту
    @Scheduled(cron = "0 * * * * *") //каждую минуту
    @SchedulerLock(name="scheduledTaskCron")
    public void scheduledTaskCron() throws InterruptedException {
        log.info("-- выполняется логика задания cron --");
    }
}
