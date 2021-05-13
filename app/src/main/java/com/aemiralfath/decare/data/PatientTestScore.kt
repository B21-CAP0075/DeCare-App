package com.aemiralfath.decare.data

data class PatientTestScore(
    var firstQuestionScore: Int = 0, //akan dikalkulasi oleh validator
    var secondQuestionScore: Int = 0, //akan dikalkulasi oleh validator
    var thirdQuestionScore: Int = 0, //akan dikalkulasi oleh sistem
    var fourthQuestionScore: Int = 0, //akan dikalkulasi oleh sistem
    var fifthQuestionScore: Int = 0, //akan dikalkulasi oleh sistem
    var sixthQuestionScore: Int = 0, //akan dikalkulasi oleh validator
    var seventhQuestionScore: Int = 0, //akan dikalkulasi oleh sistem
    var eighthQuestionScore: Int = 0, //akan dikalkulasi oleh sistem
    var ninthQuestionScore: Int = 0, //akan dikalkulasi oleh sistem
    var tenthQuestionScore: Int = 0, //akan dikalkulasi oleh validator
    var eleventhQuestionScore: Int = 0, //akan dikalkulasi oleh validator
)
