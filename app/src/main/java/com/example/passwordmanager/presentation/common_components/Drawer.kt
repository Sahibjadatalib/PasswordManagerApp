package com.example.passwordmanager.presentation.common_components

//@Composable
//fun MyDrawer(
//    modifier: Modifier = Modifier
//) {
//
//
//    Column(
//        modifier
//            .fillMaxSize()
//            .padding(16.dp, 16.dp)
//    ) {
//        Card(
//            elevation = 5.dp,
//            shape = RoundedCornerShape(5.dp)
//        ) {
//            Image(
//                painter = painterResource(id = R.drawable.password),
//                contentDescription = "image"
//            )
//
//        }
//
//        screensFromDrawer.forEach { screen ->
//            Spacer(modifier = Modifier.height(8.dp))
//            Card(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(48.dp)
//            ) {
//                Row(
//                    verticalAlignment = Alignment.CenterVertically,
//                    horizontalArrangement = Arrangement.Start,
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clickable { }
//                        .padding(8.dp)
//                ) {
//                    Icon(imageVector = screen.icon, contentDescription = "image")
//                    Spacer(modifier = Modifier.width(16.dp))
//                    Text(text = screen.label)
//                }
//            }
//
//        }
//
//    }
//}
