package com.example.challenge1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.challenge1.ui.theme.Challenge1Theme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Challenge1Theme {
                val nav = rememberNavController()
                // Fondo beige a pantalla completa
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color(0xFFE9DDBA))
                ) {
                    NavHost(navController = nav, startDestination = "welcome") {
                        composable("welcome") {
                            WelcomeScreen(
                                onLogin = { nav.navigate("login") },
                                onCreate = { nav.navigate("register") } // la podés crear luego
                            )
                        }
                        composable("login")  { LoginScreen(onBack = { nav.popBackStack() }) }
                        composable("register") { RegisterScreen(onBack = { nav.popBackStack() }) } // ✅
                    }
                }
            }
        }
    }
}

/* =============== WELCOME =============== */
@Composable
fun WelcomeScreen(
    onLogin: () -> Unit,
    onCreate: () -> Unit
) {
    val orange = Color(0xFFF28C2A)
    val grayBtn = Color(0xFF9E9E9E)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 64.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.img_inicio),
                contentDescription = "Logo Pet House",
                modifier = Modifier.size(160.dp)
            )
            Spacer(Modifier.height(16.dp))
            Text("Pet", fontSize = 48.sp, fontWeight = FontWeight.Bold, color = orange)
            Text("House", fontSize = 48.sp, fontWeight = FontWeight.Bold, color = orange)
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(bottom = 48.dp)
        ) {
            Button(
                onClick = onLogin,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = orange)
            ) { Text("Login") }

            Spacer(Modifier.height(12.dp))

            Button(
                onClick = onCreate,
                shape = RoundedCornerShape(24.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(containerColor = grayBtn)
            ) { Text("Create Account") }
        }
    }
}

/* =============== LOGIN =============== */
@Composable
fun LoginScreen(onBack: () -> Unit) {
    val orange = Color(0xFFF28C2A)
    val GrayECE = Color(0xFFECECEC)

    var email by remember { mutableStateOf("") }
    var pass  by remember { mutableStateOf("") }
    var showPass by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp)
    ) {

        Text("Iniciar sesión", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = orange)
        Spacer(Modifier.height(24.dp))

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text("¡¡Volviste!!", fontWeight = FontWeight.ExtraBold)
            Text("Iniciá sesión")
        }

        Spacer(Modifier.height(12.dp))

        Image(
            painter = painterResource(id = R.drawable.inicio_de_sesion),
            contentDescription = "Logo Pet House",
            modifier = Modifier.size(160.dp)
        )

        // -------- Campos ----------
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text("Password") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                visualTransformation = if (showPass) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    Text(
                        if (showPass) "Ocultar" else "Mostrar",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable { showPass = !showPass }
                    )
                }
            )
        }

        Spacer(Modifier.height(8.dp))

        TextButton(onClick = onBack,
            colors = ButtonDefaults.buttonColors(containerColor = orange)
        ) { Text("¿Olvidaste tu contraseña?") }

        // -------- Botón principal ----------
        Button(
            onClick = { /* TODO: lógica de login */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = orange)
        ) { Text("Ingresar") }

        Spacer(Modifier.height(8.dp))
        TextButton(onClick = onBack) { Text("Volver") }

        Spacer(Modifier.height(16.dp))

        // -------- Separador "O continuar con" ----------
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            HorizontalDivider(Modifier.weight(1f), DividerDefaults.Thickness, color = Color(0xFFE5E7EB))
            Text(
                "O continuar con",
                fontSize = 12.sp,
                color = Color(0xFF6B7280),
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            HorizontalDivider(Modifier.weight(1f), DividerDefaults.Thickness, color = Color(0xFFE5E7EB))
        }

        Spacer(Modifier.height(12.dp))

        // -------- Botones sociales (mismas “fotos”/iconos que Register) ----------
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            OutlinedButton(
                onClick = { /* TODO Google */ },
                modifier = Modifier.size(56.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color(0xFFE5E7EB)),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = GrayECE),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_google),
                    contentDescription = "Continuar con Google",
                    tint = Color.Unspecified
                )
            }

            OutlinedButton(
                onClick = { /* TODO Facebook */ },
                modifier = Modifier.size(56.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color(0xFFE5E7EB)),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = GrayECE),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_facebook),
                    contentDescription = "Continuar con Facebook",
                    tint = Color.Unspecified
                )
            }

            OutlinedButton(
                onClick = { /* TODO Apple */ },
                modifier = Modifier.size(56.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color(0xFFE5E7EB)),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = GrayECE),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_apple),
                    contentDescription = "Continuar con Apple",
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@Composable
fun RegisterScreen(onBack: () -> Unit) {
    val orange = Color(0xFFF28C2A)
    val GrayECE = Color(0xFFECECEC)
    var email by remember { mutableStateOf("") }
    var pass by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }
    var showPass by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 64.dp)
    ) {
        Text("Crear cuenta", fontSize = 28.sp, fontWeight = FontWeight.Bold, color = orange)
        Spacer(Modifier.height(12.dp))
        Text(
            "Creá una cuenta para explorar todas las ofertas",
            fontSize = 12.sp,
            color = Color(0xFF6B7280),
            modifier = Modifier.padding(horizontal = 32.dp),
            textAlign = TextAlign.Center
        )

        Spacer(Modifier.height(16.dp))

        Image(
            painter = painterResource(id = R.drawable.curita),
            contentDescription = "Logo",
            modifier = Modifier.size(160.dp)
        )

        // -------- Campos ----------
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            )

            Spacer(Modifier.height(12.dp))

            OutlinedTextField(
                value = pass,
                onValueChange = { pass = it },
                label = { Text("Password") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                visualTransformation = if (showPass) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                trailingIcon = {
                    Text(
                        if (showPass) "Ocultar" else "Mostrar",
                        modifier = Modifier
                            .padding(end = 8.dp)
                            .clickable { showPass = !showPass }
                    )
                }
            )

            OutlinedTextField(
                value = confirmPass,
                onValueChange = { confirmPass = it },
                label = { Text("Confirmar Password") },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
        }

        Spacer(Modifier.height(24.dp))

        // -------- Botón principal ----------
        Button(
            onClick = { /* TODO: validar y registrar */ },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(containerColor = orange)
        ) { Text("Registrarse") }

        Spacer(Modifier.height(8.dp))

        Text("¿Ya tenés cuenta?", fontSize = 12.sp, color = Color(0xFF6B7280))
        TextButton(onClick = onBack) { Text("Volver") }

        Spacer(Modifier.height(16.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
        ) {
            HorizontalDivider(
                Modifier.weight(1f),
                DividerDefaults.Thickness,
                color = Color(0xFFE5E7EB)
            )
            Text(
                "O continuar con",
                fontSize = 12.sp,
                color = Color(0xFF6B7280),
                modifier = Modifier.padding(horizontal = 12.dp)
            )
            HorizontalDivider(
                Modifier.weight(1f),
                DividerDefaults.Thickness,
                color = Color(0xFFE5E7EB)
            )
        }

        Spacer(Modifier.height(12.dp))

        // -------- Social logins (inline, sin auxiliares) ----------
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(horizontal = 32.dp)
        ) {
            OutlinedButton(
                onClick = { /* TODO Google */ },
                modifier = Modifier.size(56.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color(0xFFE5E7EB)),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = GrayECE),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_google),
                    contentDescription = "Continuar con Google",
                    tint = Color.Unspecified
                )
            }

            OutlinedButton(
                onClick = { /* TODO Facebook */ },
                modifier = Modifier.size(56.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color(0xFFE5E7EB)),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = GrayECE),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_facebook),
                    contentDescription = "Continuar con Facebook",
                    tint = Color.Unspecified
                )
            }

            OutlinedButton(
                onClick = { /* TODO Apple */ },
                modifier = Modifier.size(56.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color(0xFFE5E7EB)),
                colors = ButtonDefaults.outlinedButtonColors(containerColor = GrayECE),
                contentPadding = PaddingValues(0.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_apple),
                    contentDescription = "Continuar con Apple",
                    tint = Color.Unspecified
                )
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun WelcomePreview() {
    Challenge1Theme {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE9DDBA)), // fondo beige en preview también
            contentAlignment = Alignment.Center
        ) {
            WelcomeScreen(onLogin = {}, onCreate = {})
        }
    }
}

