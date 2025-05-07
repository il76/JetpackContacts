package com.il76.jetpackcontacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
                            .padding(innerPadding),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        ContactDetails(Contact(
                            name = "Киняев",
                            surname = "Джейсонович",
                            familyName = "Киняев",
                            imageRes = null,
                            isFavorite = false,
                            phone = "+7 322 222 33 22",
                            address = "000, Hollywood Boulevard, Los Angeles, CA 90028.",
                            email = "info@abc.dev"
                        ))
                    }
                }
            }
        }
    }
}

@Composable
fun ContactDetails(contact: Contact, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (contact.imageRes != null) {
            Image(
                painter = painterResource(contact.imageRes),
                contentDescription = null,
                modifier = Modifier.size(150.dp)
            )
        }
        Text(
            text = "${contact.name} ${contact.surname}".trim(),
            modifier = modifier
        )
        Text(
            text = contact.familyName,
            modifier = modifier,
            fontSize = 26.sp
        )
    }


}

@Preview(showBackground = true)
@Composable
fun ContactDetailsNophotoPreview() {
    JetpackContactsTheme {
        ContactDetails(Contact(
            name = "Фома",
            surname = "Джейсонович",
            familyName = "Киняев",
            imageRes = null,
            isFavorite = false,
            phone = "+7 322 222 33 22",
            address = "000, Hollywood Boulevard, Los Angeles, CA 90028.",
            email = "info@abc.dev"
        ))
    }
}

@Preview(showBackground = true)
@Composable
fun ContactDetailsPreview() {
    JetpackContactsTheme {
        ContactDetails(Contact(
            name = "Фома",
            surname = "Джейсонович",
            familyName = "Киняев",
            imageRes = R.drawable.matt_daymon,
            isFavorite = false,
            phone = "+7 322 222 33 22",
            address = "000, Hollywood Boulevard, Los Angeles, CA 90028.",
            email = "info@abc.dev"
        ))
    }
}