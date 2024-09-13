pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Implementando Base"
include(":app")
include(":app")




//pluginManagement {
//    repositories {
//        google {
//            content {
//                includeGroupByRegex("com\\.android.*")
//                includeGroupByRegex("com\\.google.*")
//                includeGroupByRegex("androidx.*")
//            }
//        }
//        dependencyResolutionManagement {
//            repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
//            repositories {
//                google()
//                mavenCentral()
//                // ... other repositories
//            }
//        }
//    }
//}
//
////dependencyResolutionManagement {
////    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
////    repositories {
////        // ... other repositories ...
////    }
////}
//
//dependencyResolutionManagement {
//    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
//    repositories {
//        google()
//        mavenCentral()
//    }
//}
//
//rootProject.name = "Implementando Base"
//include(":app")
//include(":app")
//