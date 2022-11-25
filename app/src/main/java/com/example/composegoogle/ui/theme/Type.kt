package com.example.composegoogle.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.composegoogle.R

// Set of Material typography styles to start with

val Montserrat = FontFamily(
    Font(R.font.montserrat_bold),
    Font(R.font.montserrat_regular, FontWeight.Bold),

)

val Typography = Typography(
    headlineLarge = TextStyle(
        fontFamily =  Montserrat,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp
    )
)