# BigBang


Highlights

- RecyclerView building is delegated to Epoxy. Ensures immutability, background diff (if any) and other cool stuff. 
- Decoupling is provided by Koin. Koin being a Service-Locator pattern is easy to get started. Personal preference over Dagger.
- Uses sealed class which act as finite response state for data response.
- ViewModel to observe data source. Since, current requirement only demands for local source. Remote sources    
  will be easy to handle in the future.
  
