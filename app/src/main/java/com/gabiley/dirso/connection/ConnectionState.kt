package com.gabiley.dirso.connection

sealed class ConnectionState {
   object Available : ConnectionState()
   object Unavailable : ConnectionState()
}
