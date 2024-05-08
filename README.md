Basic Code demo to cover:
* Compose UI
* Compose State
* Recomposition
* State Hoisting
* ViewModel as a state holder
* Unidirectional DataFlow
* DI using Hilt
* Coroutines
* Flow
* Room


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
* ViewModel as a state holder
  * If we keep the state inside the compose functions, then state will be reset when activity gets restart like config change
  * Best practice is to use ViewModel as a state holder
* Unidirectional DataFlow
  * State hoisting is the main pattern we use to build Unidirectional DataFlow in Jetpack Compose
  * Unidirectional DataFlow = When state goes down ( CallerFunction() -> MyButton() ) and event goes up ( MyButton() -> CallerFunction() )
 * Coroutines
   * We should always implement long running tasks asynchronously in a seperate thread
   * For this, latest and efficient way is Kotlin Coroutines
   * With Kotlin 1.3, we have stable Coroutine API
   * All the painful multithreading tasks done with RxJava, AsyncTask, Executors, HandlerThread, IntentService - Can be easily done with Coroutines
   * CoroutineScope(Dispatchers.IO).launch { downloadUserData() // This funcation will execute inside Coroutine }
 * Flow
   * Reactive Programming : Instead of asking all the time to the data source, in reactive programming, publisher will send event (or data) to all their subscribers
   * Flow is a coroutine that can emit multiple values sequentially
   * To observe, we have to use .collect() to get the flow updates
   * with compose, we have to get the flow as a state, hence have to use .collectAsState()
   * Cold flow : Flow dose not start producing the data unitl it's consumer start to collect then, hence cold flow
  
   Images:
   State Hoisting
   ![StateHoisting](https://github.com/rutulkotak/UnitConverterComposeDemo/assets/3943212/6a2f83ea-92a5-4fbd-b983-e84404e86c3f)
