/*
 * Copyright 2020-2022 JetBrains s.r.o. and respective authors and developers.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE.txt file.
 */

package de.mobanisto.pinpit.test.utils

import org.junit.jupiter.api.io.TempDir
import java.io.File

abstract class GradlePluginTestBase {
    @TempDir
    lateinit var testWorkDir: File

    val defaultTestEnvironment: TestEnvironment
        get() = TestEnvironment(workingDir = testWorkDir)

    val defaultAndroidxCompilerEnvironment: TestEnvironment
        get() = defaultTestEnvironment.copy(
            kotlinVersion = TestKotlinVersions.AndroidxCompatible,
            composeGradlePluginVersion = "1.2.1",
            composeCompilerArtifact = "androidx.compose.compiler:compiler:${TestProperties.androidxCompilerVersion}"
        )

    fun testProject(
        name: String,
        testEnvironment: TestEnvironment = defaultTestEnvironment,
        pinpitSubproject: Subproject? = null,
    ): TestProject =
        TestProject(name, testEnvironment = testEnvironment, pinpitSubproject = pinpitSubproject)

    fun testProject(
        name: String,
        jvmVersion: String,
        pinpitSubproject: Subproject? = null,
    ): TestProject =
        TestProject(
            name,
            testEnvironment = TestEnvironment(
                workingDir = testWorkDir,
                pinpitJvmVersion = jvmVersion
            ),
            pinpitSubproject = pinpitSubproject
        )
}
