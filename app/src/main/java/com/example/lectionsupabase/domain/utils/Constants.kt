package com.example.lectionsupabase.domain.utils

import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

class ConstantsContainer {
    object Constants {
        var  supabase: SupabaseClient = createSupabaseClient(
            supabaseUrl = "https://oruuiavqoegutrgpvugh.supabase.co",
            supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9ydXVpYXZxb2VndXRyZ3B2dWdoIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MjUzNTcyMjYsImV4cCI6MjA0MDkzMzIyNn0.567sJ3LdvvVw9HSkPCQugd2rYU9o8o1ZI1cr3J2zgf4"
        ) {
            install(Auth)
            install(Postgrest)
            install(Storage)
        }
    }
}