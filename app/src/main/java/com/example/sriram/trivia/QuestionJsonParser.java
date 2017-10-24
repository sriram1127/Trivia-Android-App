package com.example.sriram.trivia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SRIRAM on 21-09-2016.
 */
public class QuestionJsonParser {
    public static List<Question> parseQuestions(String s) {
        List<Question> questions = null;
        try {
            Question question = null;
            Choice choice = null;
            List<String> choices = null;
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.getJSONArray("questions");
            questions = new ArrayList<Question>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                question = new Question();
                question.setQuestionId(Integer.parseInt((String) jsonObject1.get("id")));
                question.setText((String) jsonObject1.get("text"));
                if (jsonObject1.has("image")) {
                    question.setImageUrl((String) jsonObject1.get("image"));
                }
                choice = new Choice();
                JSONObject jsonObject2 = jsonObject1.getJSONObject("choices");
                JSONArray jsonArray1 = jsonObject2.getJSONArray("choice");
                choices = new ArrayList<String>();
                for (int j = 0; j < jsonArray1.length(); j++) {
                    choices.add((String) jsonArray1.get(j));
                }
                choice.setChoice(choices);
                choice.setAnswer(Integer.parseInt((String) jsonObject2.get("answer")));
                question.setChoice(choice);
                questions.add(question);
            }
            return questions;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return questions;
    }
}
