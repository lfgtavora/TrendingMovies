package com.lfgtavora.designsystem.utils

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

internal const val HORIZONTAL_X_SMALL_SPACE = 4
internal const val HORIZONTAL_SMALL_SPACE = 8
internal const val HORIZONTAL_MEDIUM_SPACE = 16
internal const val HORIZONTAL_LARGE_SPACE = 24

class HorizontalSpacer { companion object }

@Composable
fun HorizontalSpacer.Companion.Xsmall() = Box(modifier = Modifier.width(HORIZONTAL_X_SMALL_SPACE.dp))

@Composable
fun HorizontalSpacer.Companion.Small() = Box(modifier = Modifier.width(HORIZONTAL_SMALL_SPACE.dp))

@Composable
fun HorizontalSpacer.Companion.Medium() = Box(modifier = Modifier.width(HORIZONTAL_MEDIUM_SPACE.dp))

@Composable
fun HorizontalSpacer.Companion.Large() = Box(modifier = Modifier.width(HORIZONTAL_LARGE_SPACE.dp))