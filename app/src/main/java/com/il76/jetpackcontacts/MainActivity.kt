package com.il76.jetpackcontacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.il76.jetpackcontacts.ui.theme.Grey
import com.il76.jetpackcontacts.ui.theme.JetpackContactsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JetpackContactsTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            colors = topAppBarColors(
                                containerColor = MaterialTheme.colorScheme.primaryContainer,
                                titleContentColor = MaterialTheme.colorScheme.primary,
                            ),
                            title = {
                                Text(stringResource(R.string.app_name))
                            }
                        )
                    },
                    modifier = Modifier
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding).fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        ContactDetails(createSampleContact())
                    }
                }
            }
        }
    }
}

@Composable
fun ContactDetails(contact: Contact) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (contact.imageRes != null) {
            Image(
                painter = painterResource(contact.imageRes),
                contentDescription = null,
                modifier = Modifier.size(200.dp).padding(top = 10.dp, bottom = 15.dp)
            )
        } else {
            RoundInitials(contact.getInitials())
        }
        Text(
            text = "${contact.name} ${contact.surname}".trim(),
            fontWeight = FontWeight.Bold
        )
        Row {
            Text(
                text = contact.familyName,
                fontSize = 26.sp
            )
            if (contact.isFavorite) {
                Image(
                    modifier = Modifier.padding(start = 4.dp)
                        .align(Alignment.CenterVertically),
                    painter = painterResource(id = android.R.drawable.star_big_on),
                    contentDescription = null
                )
            }
        }

        Column (modifier = Modifier.padding(top = 20.dp)) {
            if (contact.phone.isNotEmpty()) {
                ShowInfoRow(stringResource(R.string.phone), contact.phone)
            }
            if (contact.address.isNotEmpty()) {
                ShowInfoRow(stringResource(R.string.address), contact.address)
            }
            if (contact.email?.isNotEmpty() == true) {
                ShowInfoRow(stringResource(R.string.email), contact.email)
            }
        }
    }


}

@Composable
fun RoundInitials(initials: String) {
    Box(
        modifier = Modifier.padding(top = 10.dp, bottom = 15.dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.circle),
                contentDescription = null,
                tint = Grey
            )
            Text(
                text = initials
            )
        }
    }
}

@Composable
fun ShowInfoRow(name: String, value: String) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = "$name:",
            modifier = Modifier.padding(end = 8.dp).weight(1F),
            fontStyle = FontStyle.Italic,
            fontSize = 14.sp,
            textAlign = TextAlign.Right
        )
        Text(
            text = value,
            fontSize = 14.sp,
            modifier = Modifier.weight(1F),
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContactDetailsNophotoPreview() {
    JetpackContactsTheme {
        ContactDetails(createSampleContactNophoto())
    }
}

@Preview(showBackground = true)
@Composable
fun ContactDetailsPreview() {
    JetpackContactsTheme {
        ContactDetails(createSampleContact(R.drawable.matt_daymon))
    }
}

fun createSampleContact(imageRes: Int? = null): Contact {
    return Contact(
        name = "Фома",
        surname = "",
        familyName = "Киняев",
        imageRes = imageRes,
        isFavorite = false,
        phone = "+7 322 222 33 22",
        address = "000, Hollywood Boulevard, Los Angeles, CA 90028.",
        email = "info@abc.dev"
    )
}

fun createSampleContactNophoto(imageRes: Int? = null): Contact {
    return Contact(
        name = "Фома",
        surname = "Джейсонович",
        familyName = "Киняев",
        imageRes = null,
        isFavorite = true,
        phone = "+7 322 222 33 22",
        address = "000, Hollywood Boulevard, Los Angeles, CA 90028.",

    )
}