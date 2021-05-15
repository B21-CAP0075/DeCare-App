package com.aemiralfath.decare.util

import com.aemiralfath.decare.data.Patient
import com.google.gson.JsonObject

object JsonObjectConverter {

    fun convertPatientToJson(data: Patient) : JsonObject{
        val jsonPatient = JsonObject()
        jsonPatient.addProperty("Age", data.age)
        jsonPatient.addProperty("EDUC", data.educ)
        jsonPatient.addProperty("SES", data.ses)
        jsonPatient.addProperty("MMSE", data.mmse)

        if (data.gender == 0) {
            jsonPatient.addProperty("F", 1)
            jsonPatient.addProperty("M", 0)
        } else {
            jsonPatient.addProperty("F", 0)
            jsonPatient.addProperty("M", 1)
        }

        val json = JsonObject()
        json.add("patient", jsonPatient)

        return json
    }

}