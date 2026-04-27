package com.example.mycalc;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CalcusController {

    @FXML
    private Label resultLabel, expressionLabel;

    String currentText = "";
    Boolean isOperandSingle = false;

    // 🔢 NUMBERS
    @FXML protected void onZeroButtonClick() { resultLabel.setText(resultLabel.getText() + "0"); }
    @FXML protected void onOneButtonClick() { resultLabel.setText(resultLabel.getText() + "1"); }
    @FXML protected void onTwoButtonClick() { resultLabel.setText(resultLabel.getText() + "2"); }
    @FXML protected void onThreeButtonClick() { resultLabel.setText(resultLabel.getText() + "3"); }
    @FXML protected void onFourButtonClick() { resultLabel.setText(resultLabel.getText() + "4"); }
    @FXML protected void onFiveButtonClick() { resultLabel.setText(resultLabel.getText() + "5"); }
    @FXML protected void onSixButtonClick() { resultLabel.setText(resultLabel.getText() + "6"); }
    @FXML protected void onSevenButtonClick() { resultLabel.setText(resultLabel.getText() + "7"); }
    @FXML protected void onEightButtonClick() { resultLabel.setText(resultLabel.getText() + "8"); }
    @FXML protected void onNineButtonClick() { resultLabel.setText(resultLabel.getText() + "9"); }

    // ➕ OPERATORS
    @FXML protected void onAddButtonClick() { resultLabel.setText(resultLabel.getText() + "+"); }
    @FXML protected void onSubtractButtonClick() { resultLabel.setText(resultLabel.getText() + "-"); }
    @FXML protected void onMultiplyButtonClick() { resultLabel.setText(resultLabel.getText() + "x"); }
    @FXML protected void onDivideButtonClick() { resultLabel.setText(resultLabel.getText() + "÷"); }

    // 🧠 SPECIAL
    @FXML protected void onDotButtonClick() { resultLabel.setText(resultLabel.getText() + "."); }
    @FXML protected void onSqrtButtonClick() { resultLabel.setText(resultLabel.getText() + "√"); }

    // 🧹 BACKSPACE
    @FXML
    protected void onBackspaceButtonClick() {
        currentText = resultLabel.getText();
        if (currentText != null && !currentText.isEmpty()) {
            resultLabel.setText(currentText.substring(0, currentText.length() - 1));
        }
    }

    // 🧹 CLEAR
    @FXML
    protected void onClearButtonClick() {
        resultLabel.setText("");
        expressionLabel.setText("");
    }

    // =========================
    // VALIDATION
    // =========================
    private Boolean expressionEvaluator(String expression) {
        String expressionRegEx = "\\d*\\.?\\d+([+\\-x÷])\\d*\\.?\\d+|√\\d+";

        Pattern pattern = Pattern.compile(expressionRegEx);
        Matcher matcher = pattern.matcher(expression);

        return matcher.matches();
    }

    // =========================
    // EQUALS BUTTON
    // =========================
    @FXML
    protected void onEqualButtonClick() {
        String theExpression = resultLabel.getText();

        if (expressionEvaluator(theExpression)) {
            expressionLabel.setText(theExpression);
            String result = doTheMath(theExpression);
            resultLabel.setText(result);
        } else {
            resultLabel.setText("Wrong expression");
        }
    }

    // =========================
    // MATH ENGINE
    // =========================
    private String doTheMath(String expression) {

        char operator = ' ';
        String[] parts;

        if (expression.contains("+")) operator = '+';
        else if (expression.contains("-")) operator = '-';
        else if (expression.contains("x")) operator = 'x';
        else if (expression.contains("÷")) operator = '÷';
        else if (expression.contains("√")) operator = '√';

        parts = expression.split("[+\\-x÷]");

        double result = 0;

        try {

            double a = 0;
            double b = 0;

            if (operator == '√') {
                a = Double.parseDouble(parts[1]);
                result = Math.sqrt(a);
            } else {
                a = Double.parseDouble(parts[0]);
                b = Double.parseDouble(parts[1]);

                switch (operator) {
                    case '+': result = a + b; break;
                    case '-': result = a - b; break;
                    case 'x': result = a * b; break;
                    case '÷': result = a / b; break;
                }
            }

        } catch (Exception e) {
            return "Error";
        }

        return String.valueOf(result);
    }
}