Improvement list:

1 - separate better de StatementBuilders - Statemantable interface has two methods that in some situations needs to return null just to satisfy the contract
2 - create a pool connection or set a pool on oracle - when stress the update methods we have problems with the connection.
3 - create tests for Builders created
4 - Separate the remote interfaces to start each one in their context
5 - close windows after process.
6 - change alert to normal message

