package ru.woyfit.presentation.feature.auth

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import ru.neopharm.clubs.feature.ui.compose.custom_theme.ExtendedJetTheme
import ru.woyfit.R
import ru.woyfit.presentation.feature.ui.TopAppBarWithTitle
import ru.woyfit.presentation.feature.ui.custom_theme.MainTheme
import ru.woyfit.presentation.feature.ui.custom_theme.textStyle

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun AuthFragmentView(isLoginMethod: StateFlow<Boolean>, listener: AuthListener){
    val isLogin = isLoginMethod.collectAsState()
    println("Is Login? ${isLogin.value}")
    MainTheme {
        Scaffold(topBar = { TopAppBarWithTitle(stringResource(id = if(isLogin.value) R.string.enter_button else R.string.register_button), modifier = Modifier.background(color = Color(0xFF80B550))) }) {
            if(isLogin.value){
                LoginView(listener = listener)
            }
            else{
                SignupView(changeAuthMethod = listener::changeAuthMethod)
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun LoginView(listener: AuthListener){

    val keyboardController = LocalSoftwareKeyboardController.current
    val username = remember {
        mutableStateOf("")
    }

    val password = remember {
        mutableStateOf("")
    }

    val isPasswordVisible = remember {
        mutableStateOf(false)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(horizontal = 16.dp)) {
        TextField(
            value = username.value,
            onValueChange = {
                username.value = it
            },
            modifier = Modifier
                .padding(top = 100.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color(0xFFA8A8A8),
                    shape = RoundedCornerShape(size = 39.dp)
                )
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(size = 39.dp)
                ),
            textStyle = textStyle,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }),
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_name),
                    style = textStyle.copy(
                        color = Color(0xFF787878),
                        fontSize = 15.sp
                    ),
                )
            },
            label = null,
            shape = RoundedCornerShape(4.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.White, textColor = textStyle.color
            ),
            singleLine = true
        )
        TextField(
            value = password.value,
            onValueChange = {
                password.value = it
            },
            modifier = Modifier
                .padding(top = 24.dp)
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = Color(0xFFA8A8A8),
                    shape = RoundedCornerShape(size = 39.dp)
                )
                .background(
                    color = Color(0xFFFFFFFF),
                    shape = RoundedCornerShape(size = 39.dp)
                ),
            textStyle = textStyle,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }),
            visualTransformation = if (isPasswordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
            placeholder = {
                Text(
                    text = stringResource(R.string.enter_password),
                    style = textStyle.copy(
                        color = Color(0xFF787878),
                        fontSize = 15.sp
                    ),
                )
            },
            label = null,
            shape = RoundedCornerShape(4.dp),
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.White, textColor = textStyle.color
            ),
            singleLine = true,
            trailingIcon = {
                val image = if (isPasswordVisible.value)
                    R.drawable.ic_visibility
                else R.drawable.ic_visibility_off

                // Please provide localized description for accessibility services
                val description = if (isPasswordVisible.value) "Hide password" else "Show password"

                IconButton(onClick = {isPasswordVisible.value = !isPasswordVisible.value}){
                    Icon(painter = painterResource(id = image), contentDescription = description,
                        modifier = Modifier.size(24.dp))
                }
            }
        )

        Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(top = 32.dp)
                .shadow(
                    elevation = 4.dp,
                    spotColor = Color(0x21000000),
                    ambientColor = Color(0x21000000)
                )
                .fillMaxWidth()
                .height(40.dp)
                .background(
                    color = Color(0xFF80B550),
                    shape = RoundedCornerShape(size = 42.dp)
                )) {
            Text(
                text = stringResource(R.string.enter_button),
                style = ExtendedJetTheme.typography.body.copy(
                    fontSize = 16.sp,
                    lineHeight = 20.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    color = Color(0xFFFFFFFF),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier.clickable {
                    if(username.value.isNotEmpty() && password.value.isNotEmpty()){
                        listener.loginUser(username.value, password.value)
                    }
                    else{
                        listener.showMessage("Введите правильные данные для входа")
                    }
                }
            )
        }

        Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                text = stringResource(R.string.continue_without_registration),
                style = ExtendedJetTheme.typography.heading.copy(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF80B550),
                    textAlign = TextAlign.Center,
                ),
                modifier = Modifier
                    .padding(top = 34.dp)
                    .clickable {
                        listener.changeAuthMethod(false)
                    }
            )

            Text(
                text = stringResource(R.string.no_profile_question),
                style = ExtendedJetTheme.typography.heading.copy(
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFF373C42),
                ),
                modifier = Modifier
                    .padding(top = 48.dp)
            )

            Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(top = 20.dp)
                    .shadow(
                        elevation = 4.dp,
                        spotColor = Color(0x21000000),
                        ambientColor = Color(0x21000000)
                    )
                    .fillMaxWidth()
                    .height(40.dp)
                    .background(
                        color = Color(0xFF80B550),
                        shape = RoundedCornerShape(size = 42.dp)
                    )) {
                Text(
                    text = stringResource(R.string.register_button),
                    style = ExtendedJetTheme.typography.body.copy(
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        fontFamily = FontFamily(Font(R.font.roboto_regular)),
                        color = Color(0xFFFFFFFF),
                        textAlign = TextAlign.Center,
                    ),
                    modifier = Modifier.clickable {
                        listener.changeAuthMethod(false)
                    }
                )
            }
        }
    }
}

@Composable
fun SignupView(changeAuthMethod: (isLogin:Boolean) -> Unit){

}

@Preview(showBackground = true)
@Composable
fun AuthFragmentViewPreview(){
    AuthFragmentView(
        isLoginMethod = MutableStateFlow(true),
        listener = object : AuthListener {
            override fun changeAuthMethod(isLogin: Boolean) {

            }

            override fun registerUser(username: String, password: String) {

            }

            override fun loginUser(username: String, password: String) {

            }

            override fun showMessage(message: String) {

            }


        })
}


