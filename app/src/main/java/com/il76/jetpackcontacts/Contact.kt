package com.il76.jetpackcontacts

data class Contact(
    val name: String, //Имя
    val surname: String? = null, //Отчество
    val familyName: String, //Фамилия
    val imageRes: Int? = null,//Ресурс фотографии
    val isFavorite: Boolean = false,//Признак избранного контакта
    val phone: String, //Телефон
    val address: String, //Адрес
    val email: String? = null, //E-mail
) {
    fun getInitials(): String {
        var initials = ""
        if (name.isNotEmpty()) {
            initials += name.substring(0,1)
        }
        if (!surname.isNullOrEmpty()) {
            initials += surname.substring(0,1)
        }
        return initials
    }
}