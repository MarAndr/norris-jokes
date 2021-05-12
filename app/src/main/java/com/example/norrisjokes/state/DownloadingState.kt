package com.example.norrisjokes.state

sealed class DownloadingState {
    object LOADING: DownloadingState()
    object EMPTY: DownloadingState()
    class ERROR(val message: String): DownloadingState()
    object SUCCESS: DownloadingState()
}