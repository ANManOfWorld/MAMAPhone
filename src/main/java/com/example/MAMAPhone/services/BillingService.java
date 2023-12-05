package com.example.MAMAPhone.services;

import com.example.MAMAPhone.models.User;
import com.example.MAMAPhone.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Component
public class BillingService implements CommandLineRunner {
    private final UserRepository userRepository;
    private final UserService userService;

    private final TimeManagerService timeManagerService;

    List<User> users;
    Calendar nextCalendarOfCorrect;


    @Override
    public void run(String... args) throws Exception {
        users = new ArrayList<>(userService.list());
        //log.info("Вывод всех юзеров: " + users.toString());
        boolean flag = false;
        while (true) {
           ///////////////////////////////////////////////// users = new ArrayList<>(userService.list());

            java.time.LocalDateTime corrector = java.time.LocalDateTime.now();                          //берём текущее время
            Calendar calendarOfCorrector = Calendar.getInstance();
            calendarOfCorrector.set(corrector.getYear(), corrector.getMonthValue() - 1, corrector.getDayOfMonth(), corrector.getHour(), corrector.getMinute(), corrector.getSecond());
            if (!flag) {
                nextCalendarOfCorrect = calendarOfCorrector;
                nextCalendarOfCorrect.add(Calendar.SECOND, (timeManagerService.findTimeManager(1l).getSeconds()/2)  );
                nextCalendarOfCorrect.add(Calendar.MINUTE, timeManagerService.findTimeManager(1l).getMinutes());
                nextCalendarOfCorrect.add(Calendar.HOUR, timeManagerService.findTimeManager(1l).getHours());
                nextCalendarOfCorrect.add(Calendar.DAY_OF_WEEK, timeManagerService.findTimeManager(1l).getDays());    //   (~~~~~) 7 ЗНАЧЕНИЙ ТОЛЬКО!!! (~~~~~)
                nextCalendarOfCorrect.add(Calendar.MONTH, timeManagerService.findTimeManager(1l).getMonth());
                flag = true;
            }
            if (calendarOfCorrector.after(nextCalendarOfCorrect)) {
                log.info("Обновление юзеров!!!!!!!!!!");
                users = new ArrayList<>(userService.list());
                nextCalendarOfCorrect = calendarOfCorrector;
                nextCalendarOfCorrect.add(Calendar.SECOND, (timeManagerService.findTimeManager(1l).getSeconds()/2)  );
                nextCalendarOfCorrect.add(Calendar.MINUTE, timeManagerService.findTimeManager(1l).getMinutes());
                nextCalendarOfCorrect.add(Calendar.HOUR, timeManagerService.findTimeManager(1l).getHours());
                nextCalendarOfCorrect.add(Calendar.DAY_OF_WEEK, timeManagerService.findTimeManager(1l).getDays());    //   (~~~~~) 7 ЗНАЧЕНИЙ ТОЛЬКО!!! (~~~~~)
                nextCalendarOfCorrect.add(Calendar.MONTH, timeManagerService.findTimeManager(1l).getMonth());
            }


            for (int i = 0; i < users.size(); i++) {
                User user = users.get(i);
                Calendar connectionDate = user.getCalendar();
                if (connectionDate == null) {
                    userService.statisticOfInternet(user, 0.0);
                    userService.statisticOfMinutes(user, 0);
                    userService.statisticOfFinance(user, 0);
                    continue;
                }

                Calendar payment = user.getDateOfPayment();
                if (payment == null) {
                    user.setCurrentRate(null);
                    user.setCalendar(null);
                    user.setMinutes(0);
                    user.setInternet(0.0);
                    userService.saveUser(user);
                }


                java.time.LocalDateTime localDateTime = java.time.LocalDateTime.now();                              //берём текущее время
                //log.info("ПРОВЕРКА ВРЕМЕНИ!!!! = " + localDateTime.getMinute() + localDateTime.getSecond());
                Calendar current = Calendar.getInstance();
                current.set(localDateTime.getYear(), localDateTime.getMonthValue() - 1, localDateTime.getDayOfMonth(), localDateTime.getHour(), localDateTime.getMinute(), localDateTime.getSecond());
                //log.info("Calendar = " + current.getTime());                                                       //берём текущее время
                if (current.after(payment)) {

                   //user.setBalance(100000); //УДАЛИТЬ!!!

                    log.info("ВЫЧЕТ ДЕНЕГ!");
                    if (user.getSumOfDept() == null) {
                        user.setSumOfDept(0);
                    } else {
                        Integer sum = user.getSumOfDept() + user.getCurrentRate().getPrice();
                        user.setSumOfDept(sum);
                        userRepository.save(user);
                    }
                    /*log.info("Общая сумма платежа: " + user.getSumOfDept());
                    log.info("Дата текущая = " + current.getTime());
                    log.info("Дата оплаты = " + payment.getTime());
*/
                    //payment.add(Calendar.SECOND,30);
                    Long id = 1L;
                    payment.add(Calendar.SECOND, timeManagerService.findTimeManager(id).getSeconds());
                    payment.add(Calendar.MINUTE, timeManagerService.findTimeManager(id).getMinutes());
                    payment.add(Calendar.HOUR, timeManagerService.findTimeManager(id).getHours());
                    payment.add(Calendar.DAY_OF_WEEK, timeManagerService.findTimeManager(id).getDays());    //   (~~~~~) 7 ЗНАЧЕНИЙ ТОЛЬКО!!! (~~~~~)
                    payment.add(Calendar.MONTH, timeManagerService.findTimeManager(id).getMonth());       //ОТ 0 ДО 11!!!!
                    if ((current.before(payment)) && (user.getSumOfDept() != null)) {
                        if (user.getBalance() >= user.getSumOfDept()) {
                            user.setBalance(user.getBalance() - user.getSumOfDept()  /*user.getCurrentRate().getPrice()*/);

                            log.info("Оплачено = " + user.getSumOfDept());
                            userService.statisticOfInternet(user, user.getCurrentRate().getCountOfTrafficInternet() - user.getInternet());
                            userService.statisticOfMinutes(user, user.getCurrentRate().getCountOfMinutes() - user.getMinutes());
                            userService.statisticOfFinance(user, user.getSumOfDept());

                            user.setSumOfDept(0);
                            user.setMinutes(user.getCurrentRate().getCountOfMinutes());
                            user.setInternet(user.getCurrentRate().getCountOfTrafficInternet());
                            userRepository.save(user);
                        } else {
                            user.setBalance(user.getBalance() - user.getSumOfDept()  /*user.getCurrentRate().getPrice()*/);

                            userService.statisticOfInternet(user, 0.0);
                            userService.statisticOfMinutes(user, 0);
                            userService.statisticOfFinance(user, user.getSumOfDept());
                            log.info("Оплачено = " + user.getSumOfDept());
                            user.setSumOfDept(0);

                            user.setMinutes(0);
                            user.setInternet(0.0);
                            user.setCurrentRate(null);
                            user.setDateOfPayment(null);
                            user.setCalendar(null);

                            userRepository.save(user);
                        }
                    }

                    user.setCalendar(payment);
                }
            } /*кц for'a*/


        }
    }
}



 /*public void checkDate() {
        List<User> users = new ArrayList<>(userService.list());
        log.info("Вывод всех юзеров: " + users.toString());
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            Calendar date = user.getCalendar();
            //date.roll(Calendar.MONTH, 1);
            date.roll(Calendar.SECOND, 2);
            if (user.getCalendar().equals(date)) {
                log.info("ВЫЧЕТ ДЕНЕГ!");
                user.setBalance(user.getBalance() - user.getCurrentRate().getPrice());
            }
        }
    }*/

