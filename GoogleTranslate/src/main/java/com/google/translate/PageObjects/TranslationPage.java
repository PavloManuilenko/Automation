package com.google.translate.PageObjects;

public interface TranslationPage {

    void openThePage();

    void enterATextForTranslation(String text);

    Boolean isThereSomethingInTheResultBox();

    String getTextFromTheResultBox();

}
