package net.dotevolve.base.utils;

import java.time.LocalDate;
import java.time.Period;

import org.springframework.stereotype.Component;

@Component
public class ConversionUtils {

    public double satToAct(double sat_composite, double act_composite) {
        int[] satThresholds = {
                1570, 1530, 1490, 1450, 1420, 1390, 1360, 1330, 1300, 1260, 1230, 1200,
                1160, 1130, 1100, 1060, 1030, 990, 960, 920, 880, 830, 780, 730, 690, 650, 620, 590
        };
        int[] actScores = {
                36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25,
                24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9
        };

        int converted_act_composite = 0;
        if (sat_composite > 0) {
            for (int i = 0; i < satThresholds.length; i++) {
                if (sat_composite >= satThresholds[i]) {
                    converted_act_composite = actScores[i];
                    break;
                }
            }
        }

        if (act_composite > 0) {
            act_composite = Math.max(converted_act_composite, act_composite);
        } else {
            act_composite = converted_act_composite;
        }
        return act_composite;
    }

    public double cltToAct(double clt_composite, double act_composite) {
        int[] cltThresholds = {
                111, 107, 103, 100, 97, 94, 92, 89, 86, 84, 81, 78, 76, 74, 72, 68, 66, 64, 61, 57, 55, 52, 49, 46, 44, 40, Integer.MIN_VALUE
        };
        int[] actScores = {
                36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10
        };

        int converted_act_composite = 0;
        if (clt_composite > 0) {
            for (int i = 0; i < cltThresholds.length; i++) {
                if (clt_composite >= cltThresholds[i]) {
                    converted_act_composite = actScores[i];
                    break;
                }
            }
        }

        if (act_composite > 0) {
            act_composite = Math.max(converted_act_composite, act_composite);
        } else {
            act_composite = converted_act_composite;
        }
        return act_composite;
    }

    public int dobToAge(String dob) { // dob should be in yyyy-mm-dd format

        LocalDate dateOfBirth = LocalDate.parse(dob);
        LocalDate curDate = LocalDate.now();
        return Period.between(dateOfBirth, curDate).getYears();
    }

    public double actToSat(double act) {
        int[] actThresholds = {
                36, 35, 34, 33, 32, 31, 30, 29, 28, 27, 26, 25, 24, 23, 22, 21, 20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 0
        };
        int[] satScores = {
                1590, 1540, 1500, 1460, 1430, 1400, 1370, 1340, 1310, 1280, 1240, 1210, 1180, 1140, 1110, 1080, 1040, 1010, 970, 930, 890, 850, 800, 760, 710, 670, 630, 590, 550, 510, 470, 430, 400
        };

        for (int i = 0; i < actThresholds.length; i++) {
            if (act >= actThresholds[i]) {
                return satScores[i];
            }
        }
        return 400;
    }

}
