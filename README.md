Basic Code demo to cover:
* Compose UI
* Compose State
* Recomposition
* State Hoisting
* Unidirectional DataFlow
* DI using Hilt
* Coroutines
* Flow


Compose
* Mordern toolkit for Android UI development
* State
  * App display the state to the user. State in application is, any value that can change over time
  * Compose has a reactive approach. In order to display the changes of the state of the UI, state must be observable by compose runtime
  * To make state change observable by compose, we have many options like MutableState, LiveData, Flow, RxJava, etc
* Recomposition
  * Compose runtime creats the UI by calling composable functions
  * If the data changes ( state changes ), compose runtime reexecutes those composable functions with new data and creats an updated UI
  * Which is called Recomposition. For which to happen, changes of the state of UI must be observable by compose
  * To make state change observable by compose, we have many options like MutableState, LiveData, Flow, RxJava, etc
* remember
  * var count by remember { mutableIntStateOf(0) }
  * Value stored by "remember" is stored during the initial composition and returned during recomposition
  * remember value will be reset, when activity restarts ( may be due to configuration change such as screen rotation, language change and keyboard change )
* rememberSaveable
  * var count by rememberSaveable { mutableIntStateOf(0) }
  * If we want our state in a composable to preserve during configuration change, then use "rememberSaveable"
* State hoisting
  * Composable with internal state are less resuable and very difficult to test
  * Best practice is to moving the state up to the caller and make composable stateless
  * Attached StateHoisting.jpg at the end
* Unidirectional DataFlow
  * State hoisting is the main pattern we use to build Unidirectional DataFlow in Jetpack Compose
  * Unidirectional DataFlow = When state goes down ( CallerFunction() -> MyButton() ) and event goes up ( MyButton() -> CallerFunction() )

