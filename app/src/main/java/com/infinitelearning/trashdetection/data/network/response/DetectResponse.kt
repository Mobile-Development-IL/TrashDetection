package com.infinitelearning.trashdetection.data.network.response

import com.google.gson.annotations.SerializedName

data class DetectResponse(

	@field:SerializedName("class_category")
	val classCategory: String? = null,

	@field:SerializedName("accuracy")
	val accuracy: String? = null,

	@field:SerializedName("description")
	val description: String? = null
)
