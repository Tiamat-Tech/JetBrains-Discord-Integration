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

package com.almightyalpaca.jetbrains.plugins.discord.plugin.settings.impl

import com.almightyalpaca.jetbrains.plugins.discord.plugin.settings.ProjectSettings
import com.almightyalpaca.jetbrains.plugins.discord.plugin.settings.options.impl.PersistentStateOptionHolderImpl
import com.almightyalpaca.jetbrains.plugins.discord.plugin.settings.options.types.check
import com.almightyalpaca.jetbrains.plugins.discord.plugin.settings.options.types.text
import com.almightyalpaca.jetbrains.plugins.discord.plugin.settings.options.types.toggle
import com.almightyalpaca.jetbrains.plugins.discord.plugin.settings.options.types.toggleable
import com.almightyalpaca.jetbrains.plugins.discord.plugin.settings.settings
import com.almightyalpaca.jetbrains.plugins.discord.plugin.settings.values.NewProjectShow
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.project.Project

@State(name = "DiscordProjectSettings", storages = [Storage("discord.xml")])
class ProjectSettingsImpl(override val project: Project) : ProjectSettings, PersistentStateOptionHolderImpl() {
    override val show by check("Show this project in Rich Presence", settings.newProjectShow.get() == NewProjectShow.SHOW)

    private val nameOverrideToggle by toggleable<Boolean>()
    override val nameOverrideEnabled by nameOverrideToggle.toggle.check("Override project name", false)
    override val nameOverrideText by nameOverrideToggle.option.text("", "")

    override val description by text("Project description", "")
}
