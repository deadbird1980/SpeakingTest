package com.core.test;

import com.core.lesson.*;
import com.core.util.Utils;
import org.json.*;
import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.border.*;


public class SurveyPage extends LessonPage {

    private JPanel contentPanel;
    private JSONObject survey;
    private ButtonGroup groupSelfRating;

    private JTextField textFirstName;
    private JTextField textLastName;
    private JTextField textMiddleName;
    private JTextField textEmail;
    private JTextField textCountry;
    private JTextField textLanguage;
    private JTextField textYears;
    private JTextField textMajorField;
    private JTextField textTimeSpent;
    private JTextField textAge;
    private JTextField textGender;
    private JTextField textOEPTScore;
    private JTextField textTOEFLSpeakingScore;
    private JTextField textTSEScore;
    private JTextField textIELTSSpeakingScore;

    public SurveyPage(JSONObject json) {
        survey = json;
        contentPanel = getContentPanel();
        contentPanel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        setLayout(new java.awt.BorderLayout());

        JPanel secondaryPanel = new JPanel();
        secondaryPanel.add(contentPanel, BorderLayout.NORTH);
        add(secondaryPanel, BorderLayout.CENTER);
    }

    public HashMap getSubmit() {
        LinkedHashMap data = new LinkedHashMap();
        String name = textFirstName.getText()+" "+textMiddleName.getText()+" "+textLastName.getText();
        data.put("name", name);
        data.put("email", textEmail.getText());
        data.put("country", textCountry.getText());
        data.put("language", textLanguage.getText());
        data.put("years", textYears.getText());
        data.put("major field", textMajorField.getText());
        data.put("self rating", Utils.getSelection(groupSelfRating).getText());
        data.put("age", textAge.getText());
        data.put("gender", textGender.getText());
        data.put("OEPT score", textOEPTScore.getText());
        data.put("TOEFL Speaking score", textTOEFLSpeakingScore.getText());
        data.put("TSE score", textTSEScore.getText());
        data.put("IELTS score", textIELTSSpeakingScore.getText());
        return data;
    }


    private JPanel getContentPanel() {
        JPanel contentPanel = new JPanel();
        //contentPanel.setLayout(new GridLayout());
        contentPanel.setLayout(new java.awt.BorderLayout());
        JLabel textLabel = new JLabel();
        textLabel.setFont(new Font("MS Sans Serif", Font.BOLD, 20));
        try {
            textLabel.setText(survey.getString("title"));
        } catch (JSONException e) {
            textLabel.setText("Participant Information Form");
        }
        textLabel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));
        JPanel introPanel = createIntroPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.add(textLabel, BorderLayout.NORTH);
        contentPanel.add(introPanel, BorderLayout.CENTER);

        return contentPanel;

    }

    private JPanel createIntroPanel() {
        JPanel introPanel = new JPanel();
        //Group the radio buttons.
        introPanel.setLayout(new GridBagLayout());
        introPanel.setBorder(new LineBorder(Color.BLACK, 1));
        introPanel.setPreferredSize(new Dimension(650,600));
        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.BOTH;
        gBC.weightx = 0;
        gBC.gridx = 0;
        gBC.gridy = 0;
        gBC.anchor = GridBagConstraints.CENTER;
        gBC.fill = GridBagConstraints.BOTH;
        gBC.insets=new Insets(2, 2, 2, 2);
        try {
            //Name
            JLabel labelName = createLabel(survey.getString("nameLabel"));
            textFirstName = new JTextField();
            textLastName = new JTextField();
            textMiddleName = new JTextField();
            //textFirstName.setPreferredSize(new Dimension(80,20));
            //textLastName.setPreferredSize(new Dimension(80,20));
            //textMiddleName.setPreferredSize(new Dimension(80,20));
            gBC.gridy = 0;
            gBC.gridx = 0;
            //gBC.gridheight = 2;
            //gBC.ipady = 20;     //This component has more breadth compared to other buttons
            gBC.weightx = 0.0;
            introPanel.add(labelName, gBC);
            gBC.gridy = 0;
            gBC.gridx = 1;
            gBC.gridwidth=3;
            JPanel namePanel = new JPanel();
            namePanel.setPreferredSize(new Dimension(300, 30));
            namePanel.setLayout(new GridLayout(2, 3));
            namePanel.add(textLastName);
            namePanel.add(textFirstName);
            namePanel.add(textMiddleName);
            namePanel.add(createLabel(survey.getString("firstName")));
            namePanel.add(createLabel(survey.getString("firstName")));
            namePanel.add(createLabel(survey.getString("firstName")));
            namePanel.setBorder (new LineBorder(Color.blue, 3));
            introPanel.add(namePanel, gBC);
            //Email
            JLabel labelEmail = createLabel(survey.getString("emailLabel"));
            textEmail = new JTextField();
            textEmail.setPreferredSize(new Dimension(40, 20));
            gBC.gridwidth=1;
            gBC.gridy = 1;
            gBC.gridx = 0;
            introPanel.add(labelEmail, gBC);
            gBC.gridy = 1;
            gBC.gridx = 1;
            gBC.gridwidth = 3;
            introPanel.add(textEmail, gBC);
            //Country
            JLabel labelCountry = createLabel("<html>"+survey.getString("countryLabel")+"</html>");
            labelCountry.setPreferredSize(new Dimension(40, 20));
            textCountry = new JTextField();
            textCountry.setPreferredSize(new Dimension(40, 20));
            gBC.gridwidth = 1;
            gBC.gridy = 2;
            gBC.gridx = 0;
            introPanel.add(labelCountry, gBC);
            gBC.gridy = 2;
            gBC.gridx = 1;
            introPanel.add(textCountry, gBC);
            //Language
            JLabel labelLanguage = createLabel(survey.getString("languageLabel"));
            textLanguage = new JTextField();
            textLanguage.setPreferredSize(new Dimension(100, 20));
            gBC.gridy = 2;
            gBC.gridx = 2;
            introPanel.add(labelLanguage, gBC);
            gBC.gridy = 2;
            gBC.gridx = 3;
            introPanel.add(textLanguage, gBC);
            //Time
            JLabel labelYears = createLabel("<html>"+survey.getString("timeLabel")+"</html>");
            labelYears.setBorder (new LineBorder(Color.blue, 3));
            textYears = new JTextField();
            textYears.setPreferredSize(new Dimension(70, 20));
            gBC.gridy = 3;
            gBC.gridx = 0;
            introPanel.add(labelYears, gBC);
            gBC.gridy = 3;
            gBC.gridx = 1;
            introPanel.add(textYears, gBC);
            //Major
            JLabel labelMajor = createLabel(survey.getString("majorLabel"));
            textMajorField = new JTextField();
            textMajorField.setPreferredSize(new Dimension(40, 20));
            gBC.gridy = 3;
            gBC.gridx = 2;
            introPanel.add(labelMajor, gBC);
            gBC.gridy = 3;
            gBC.gridx = 3;
            introPanel.add(textMajorField, gBC);
            //Age
            JLabel labelAge = createLabel(survey.getString("ageLabel"));
            textAge = new JTextField();
            textAge.setPreferredSize(new Dimension(40, 20));
            gBC.gridy = 4;
            gBC.gridx = 0;
            introPanel.add(labelAge, gBC);
            gBC.gridy = 4;
            gBC.gridx = 1;
            introPanel.add(textAge, gBC);
            //gender
            JLabel labelGender = createLabel(survey.getString("genderLabel"));
            textGender = new JTextField();
            textGender.setPreferredSize(new Dimension(40, 20));
            gBC.gridy = 4;
            gBC.gridx = 2;
            introPanel.add(labelGender, gBC);
            gBC.gridy = 4;
            gBC.gridx = 3;
            introPanel.add(textGender, gBC);
            //Self rating of speaking ablility
            //situationLabel.setPreferredSize(new Dimension(100, 20));
            //self rating
            JSONObject rating = survey.getJSONObject("selfrating");
            JLabel labelRating = createLabel("<html>"+rating.getString("label")+"</html>");
            labelRating.setPreferredSize(new Dimension(30,20));
            //buttons
            JPanel buttonsPanel = new JPanel();
            JSONArray options = rating.getJSONArray("options");
            groupSelfRating = new ButtonGroup();
            buttonsPanel.setPreferredSize(new Dimension(240, 10));
            buttonsPanel.setBorder(new LineBorder(Color.BLACK, 1));
            for(int j=0; j<options.length(); j++) {
                JRadioButton radioButton = new JRadioButton(options.getString(j));
                radioButton.setActionCommand(options.getString(j));
                //radioButton.setSelected(true);
                groupSelfRating.add(radioButton);
                buttonsPanel.add(radioButton);
            }
            gBC.gridy = 5;
            gBC.gridx = 0;
            introPanel.add(labelRating, gBC);
            gBC.gridy = 5;
            gBC.gridx = 1;
            gBC.gridwidth = 3;
            introPanel.add(buttonsPanel, gBC);
            //scoreDescription
            gBC.gridy = 6;
            gBC.gridx = 0;
            gBC.gridwidth=4;
            JEditorPane instruction = createInstruction();
            introPanel.add(instruction, gBC);
            //OEPT score
            JLabel labelOEPT = createLabel(survey.getString("oeptScoreLabel"));
            textOEPTScore = new JTextField();
            textOEPTScore.setPreferredSize(new Dimension(40, 20));
            gBC.gridy = 7;
            gBC.gridx = 0;
            introPanel.add(labelOEPT, gBC);
            gBC.gridy = 7;
            gBC.gridx = 2;
            gBC.gridwidth = 2;
            introPanel.add(textOEPTScore, gBC);
            //Speaking score
            JLabel labelTOEFLSpeakingScore = createLabel(survey.getString("toeflScoreLabel"));
            textTOEFLSpeakingScore = new JTextField();
            textTOEFLSpeakingScore.setPreferredSize(new Dimension(40, 20));
            gBC.gridy = 8;
            gBC.gridx = 0;
            introPanel.add(labelTOEFLSpeakingScore, gBC);
            gBC.gridy = 8;
            gBC.gridx = 2;
            gBC.gridwidth = 2;
            introPanel.add(textTOEFLSpeakingScore, gBC);
            //TSE score
            JLabel labelTSEScore = createLabel(survey.getString("tseScoreLabel"));
            textTSEScore = new JTextField();
            textTSEScore.setPreferredSize(new Dimension(40, 20));
            gBC.gridy = 9;
            gBC.gridx = 0;
            introPanel.add(labelTSEScore, gBC);
            gBC.gridy = 9;
            gBC.gridx = 2;
            gBC.gridwidth = 2;
            introPanel.add(textTSEScore, gBC);
            //IELTS score
            JLabel labelIELTSSpeakingScore = createLabel(survey.getString("ieltsScoreLabel"));
            textIELTSSpeakingScore = new JTextField();
            textIELTSSpeakingScore.setPreferredSize(new Dimension(40, 20));
            gBC.gridy = 10;
            gBC.gridx = 0;
            introPanel.add(labelIELTSSpeakingScore, gBC);
            gBC.gridy = 10;
            gBC.gridx = 2;
            gBC.gridwidth = 2;
            introPanel.add(textIELTSSpeakingScore, gBC);
        } catch (JSONException e) {
            System.out.println("failed to get the JSON String");
        }
        introPanel.setBorder (new LineBorder(Color.blue, 3));
        return introPanel;
    }
    private JEditorPane createInstruction() {
        JEditorPane introArea = new JEditorPane();
        //introArea.setPreferredSize(new Dimension(200,400));
        introArea.setContentType("text/html; charset=utf-8");
        //introArea.setText((String)textContent);
        try {
            introArea.setText(survey.getString("scoreDescription"));
        } catch (JSONException e) {
            System.out.println("error when trying to get json value");
        }
        introArea.setEditable(false);
        //Get JFrame background color
        Color color = getBackground();
        introArea.setBackground(color);
        //instructionPanel.add(introArea, BorderLayout.CENTER);
        return introArea;
    }


    private JLabel createLabel(String str) {
        JLabel label = new JLabel();
        label.setText(str);
        return label;
    }


}
