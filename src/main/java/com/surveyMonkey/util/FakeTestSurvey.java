package com.surveyMonkey.util;

import com.surveyMonkey.entities.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FakeTestSurvey {
    private Survey testSurvey;
    private Random random;
    public FakeTestSurvey(){
        populate();
    }
    private void populate(){
        testSurvey=new Survey("reserved","reserved");
        QuestionAnswerWrapper qaw1=new QuestionAnswerWrapper(new OpenEndedQuestion("What is green?"));
        List<Answer> a1=new ArrayList<>();
        a1.add(new Answer("Pickles"));
        a1.add(new Answer("Grass"));
        a1.add(new Answer("Unripe bananas"));
        a1.add(new Answer("Moss"));
        a1.add(new Answer("Leaves"));
        a1.add(new Answer("Frog"));
        qaw1.setAnswers(a1);
        QuestionAnswerWrapper qaw2=new QuestionAnswerWrapper(new HistoQuestion("How are you today? (1-100)",1,100,1));
        List<Answer> a2=new ArrayList<>();
        for(int i=0;i<900;i++){
            a2.add(new Answer(Integer.toString(random.nextInt(100)+1)));
        }
        qaw2.setAnswers(a2);
        List<String> options=Arrays.asList(new String[]{"Green","Blue","Red"});
        a1=new ArrayList<>();
        QuestionAnswerWrapper qaw3=new QuestionAnswerWrapper(new OptionQuestion("What color is the best?",new ArrayList<>(options)));
        for(int i=0;i<900;i++){
            a1.add(new Answer(options.get(random.nextInt(3))));
        }
        qaw3.setAnswers(a1);
        List<QuestionAnswerWrapper> qa=new ArrayList<>();
        qa.add(qaw1);
        qa.add(qaw2);
        qa.add(qaw3);
        testSurvey.setSurvey(qa);
    }
}
