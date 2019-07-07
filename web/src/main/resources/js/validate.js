function validate_email() {
   var reg = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
   var address = document.getElementById('email').value;
   if(reg.test(address) == false) {
		document.getElementById('message_email').style.color = 'red';
		document.getElementById('message_email').innerHTML = 'not email';
   } else {
		document.getElementById('message_email').style.color = 'green';
		document.getElementById('message_email').innerHTML = 'email correct';<ul></ul>
   }
}

function validate_password() {
   var reg = /^([A-Za-z0-9_\-\.\s])$/;
   var password = document.getElementById('email').value;
   if(password == "" || password.length < 7 || reg.test(password) == false) {
		document.getElementById('message_password').style.color = 'red';
		document.getElementById('message_password').innerHTML = 'password incorrect or less than 7 symbols';
   } else {
		document.getElementById('message_password').style.color = 'green';
		document.getElementById('message_password').innerHTML = 'email correct';<ul></ul>
   }
}

 var check_confirm_password() {
      if (document.getElementById('password').value ==
          document.getElementById('confirm_password').value) {
			document.getElementById('message_confirm').style.color = 'green';
			document.getElementById('message_confirm').innerHTML = 'matching';
      } else {
			document.getElementById('message_confirm').style.color = 'red';
			document.getElementById('message_confirm').innerHTML = 'not matching';
      }
  }
  
  function ValidatePhoneNumber() {
  var regExp = /^[0-9]{3}-[0-9]{2}-[0-9]{7}/;
  var phone = document.getElementById('phone').value;
  if (phone.match(regExp)) {
		document.getElementById('message_phone').style.color = 'green';
		document.getElementById('message_phone').innerHTML = 'Correct';
  }
		document.getElementById('message_phone').style.color = 'red';
		document.getElementById('message_phone').innerHTML = 'Incorrect phone number';
}