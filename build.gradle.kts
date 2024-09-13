//buildscript {
//    repositories {
//        google()
//        mavenCentral()
//    }
//    dependencies {
//        classpath (libs.gradle )// Ou a versão mais recente
//        classpath (libs.google.services) // Plugin do Google Services
//    }
//}

//allprojects {
//    repositories {
//        google()
//        mavenCentral()
//    }
//}



// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
}