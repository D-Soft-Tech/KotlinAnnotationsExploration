package com.dsofttech.annotations.data.annotations

@Target(AnnotationTarget.FIELD)
@MustBeDocumented
/**
This is an annotation to specify the date format permitted
 */
annotation class AllowedDateFormat(val regex: String)
