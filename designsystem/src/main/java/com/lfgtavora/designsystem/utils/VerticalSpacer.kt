package com.lfgtavora.designsystem.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

const val VERTICAL_X_SMALL_SPACE = 4
const val VERTICAL_SMALL_SPACE = 8
const val VERTICAL_MEDIUM_SPACE = 16
const val VERTICAL_LARGE_SPACE = 24

class VerticalSpacer {
    companion object
}

@Composable
fun VerticalSpacer.Companion.Xsmall() = Box(modifier = Modifier.height(VERTICAL_X_SMALL_SPACE.dp))

@Composable
fun VerticalSpacer.Companion.Small() = Box(modifier = Modifier.height(VERTICAL_SMALL_SPACE.dp))

@Composable
fun VerticalSpacer.Companion.Medium() = Box(modifier = Modifier.height(VERTICAL_MEDIUM_SPACE.dp))

@Composable
fun VerticalSpacer.Companion.Large() = Box(modifier = Modifier.height(VERTICAL_LARGE_SPACE.dp))