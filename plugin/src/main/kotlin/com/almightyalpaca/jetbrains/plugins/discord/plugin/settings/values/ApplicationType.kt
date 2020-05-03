/*
 * Copyright 2017-2020 Aljoscha Grebe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.almightyalpaca.jetbrains.plugins.discord.plugin.settings.values

import com.almightyalpaca.jetbrains.plugins.discord.plugin.settings.options.types.SimpleValue
import com.almightyalpaca.jetbrains.plugins.discord.plugin.settings.options.types.ToolTipProvider
import com.intellij.openapi.application.ApplicationNamesInfo
import java.util.*

typealias ApplicationTypeValue = SimpleValue<ApplicationType>

enum class ApplicationType(val description: String, override val toolTip: String? = null) : ToolTipProvider {
    JETBRAINS("JetBrains IDE") {
        override val applicationNameReadable = "JetBrains IDE"
    },
    IDE("IDE Name", toolTip = "e.g. IntelliJ IDEA") {
        override val applicationNameReadable: String by lazy {
            ApplicationNamesInfo.getInstance()
                .fullProductName
        }
    },
    IDE_EDITION("IDE Name and Edition", toolTip = """e.g. IntelliJ IDEA Ultimate""""") {
        override val applicationNameReadable: String by lazy {
            ApplicationNamesInfo.getInstance()
                .fullProductNameWithEdition
                .replace("Edition", "")
                .trim()
        }
    };

    abstract val applicationNameReadable: String

    val applicationName by lazy {
        applicationNameReadable.split(' ')
            .asSequence()
            .map { it.toLowerCase(Locale.ENGLISH) }
            .joinToString(separator = "_")
    }

    override fun toString() = description
}
