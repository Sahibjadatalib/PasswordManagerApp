package com.stalib.passwordmanager.util

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri


val suggestionsIntent = Intent(Intent.ACTION_SEND).apply {
    putExtra(Intent.EXTRA_EMAIL, arrayOf("stalib420@gmail.com"))
    putExtra(Intent.EXTRA_SUBJECT, "Suggestions")
    putExtra(Intent.EXTRA_TEXT, "i want to suggest you...")
    type = "message/rfc822"
}

val contactUsIntent = Intent(Intent.ACTION_SEND).apply {
    putExtra(Intent.EXTRA_EMAIL, arrayOf("stalib420@gmail.com"))
    putExtra(Intent.EXTRA_SUBJECT, "Contact")
    putExtra(Intent.EXTRA_TEXT, "hey, I want to contact you...")
    type = "message/rfc822"
}

val reportBugIntent = Intent(Intent.ACTION_SEND).apply {
    putExtra(Intent.EXTRA_EMAIL, arrayOf("stalib420@gmail.com"))
    putExtra(Intent.EXTRA_SUBJECT, "Bug Report")
    putExtra(Intent.EXTRA_TEXT, "i want to report a bug...")
    type = "message/rfc822"
}

val rateAppIntent = Intent(Intent.ACTION_VIEW).apply {
    data = try {
        Uri.parse("market://details?id=com.stalib.passwordmanager")
    } catch (e: ActivityNotFoundException) {
        Uri.parse("https://play.google.com/store/apps/details?id=com.stalib.passwordmanager")
    }
}

val privacyPolicyIntent = Intent(Intent.ACTION_VIEW).apply {
    data = Uri.parse("https://sites.google.com/view/pk0722/home?authuser=4")
}