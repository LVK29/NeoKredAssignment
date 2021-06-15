let createEmployeeBtn = document.getElementById("createEmployeeBtn");
let createAnotherEmployeeBtn = document.getElementById(
  "createAnotherEmployeeBtn"
);
let dataBlock = document.getElementById("dataBlock");
let resultBlock = document.getElementById("resultBlock");

function createAnotherEmployee() {
  dataBlock.style.display = "block";
  resultBlock.style.display = "none";
  resultBlock.style.background = "white";
  createAnotherEmployeeBtn.style.display = "none";
}

function createEmployee() {
  let employeeFirstName = document.getElementById("firstNameTxt");
  let employeeLastName = document.getElementById("lastNameTxt");
  if (
    !isAlphabets(employeeFirstName.value.trim() + employeeLastName.value.trim())
  ) {
    return;
  }
  axios
    .request({
      url: "/employee",
      method: "post",
      baseURL: "http://localhost:999",
      data: {
        firstName: employeeFirstName.value.trim(),
        lastName: employeeLastName.value.trim(),
      },
    })
    .then(function (response) {
      employeeFirstName.value = "";
      employeeLastName.value = "";
      response = response.data;
      let employeeFullName = document.getElementById("employeeFullName");
      let employeeEmail = document.getElementById("employeeEmail");
      let employeeID = document.getElementById("employeeID");

      employeeFullName.textContent =
        response.firstName + " " + response.lastName;
      employeeEmail.textContent = response.email;
      employeeID.textContent = response.employeeId;

      dataBlock.style.display = "none";
      resultBlock.style.display = "block";
      resultBlock.style.background = "#94fc03";
      createAnotherEmployeeBtn.style.display = "block";
    })
    .catch(function (error) {
      console.log(error, "Error on Saving Employee");
    });
}

function isAlphabets(inputtxt) {
  let letterNumber = /^[a-zA-Z]+$/;
  if (inputtxt.match(letterNumber)) {
    return true;
  } else {
    alert("Please enter only Alphabets");
    return false;
  }
}

createEmployeeBtn.addEventListener("click", createEmployee);
createAnotherEmployeeBtn.addEventListener("click", createAnotherEmployee);
