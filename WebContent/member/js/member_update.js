const dropArea = document.querySelector(".drag-area");
const dragText = document.querySelector("header");
const browseBtn = document.querySelector(".browse_btn");
const hiddenInput = document.querySelector(".hidden_input");

// drag area
// $(document).ready(function () {
//   $(".member_profile_pic").draggable();
// });

let file;
browseBtn.onclick = () => {
  hiddenInput.click(); // if the user clicks the customized button, the hidden
  // input is clicked as well
};

hiddenInput.addEventListener("change", function () {
  file = this.files[0];
  showFile();
});

// inside drop area
dropArea.addEventListener("dragover", (e) => {
  e.preventDefault();
  // console.log("file dragged over drag area");
  dropArea.classList.add("active");
  dragText.textContent = "Release to upload file";
});

// outside drop area
dropArea.addEventListener("dragleave", () => {
  // console.log("file outside drag area");
  dropArea.classList.remove("active");
  dragText.textContent = "Drag & drop to upload file";
});

// file dropped in droparea
dropArea.addEventListener("drop", (e) => {
  e.preventDefault();
  // console.log("file outside drag area");
  file = e.dataTransfer.files[0];
  showFile();
});

let fileURL;
let imgTag;

function showFile() {
  let fileType = file.type;
  let validExtensions = ["image/jpeg", "image/jpg", "image/png"];
  if (validExtensions.includes(fileType)) {
    let fileReader = new FileReader(); // creating new file object
    fileReader.onload = () => {
      fileURL = fileReader.result; // passing user file source in fileURL
      // variable
      imgTag = `<img src= ${fileURL} alt= "" >`;
      dropArea.innerHTML = imgTag;
    };
    fileReader.readAsDataURL(file);
  } else {
    alert("Not an image");
    dropArea.classList.remove("active");
  }
}

// INITIAL STUFF ON STIE //
fetch("findMemberById")
  .then((body) => body.json())
  .then((member) => {
    const bytesStr = atob(member.memberProfilePic); // this is the property in the entity
    let len = bytesStr.length;
    const u8Array = new Uint8Array(len);
    while (len--) {
      u8Array[len] = bytesStr.charCodeAt(len);
    }
    const blob = new Blob([u8Array]);
    const url = URL.createObjectURL(blob);
    console.log(url);
    document.querySelector(".member_profile .member_profile_pic").src = url;
    document.querySelector(".member-name").value = member.memberName;
    document.querySelector(".member-username").value = member.memberUsername;
    document.querySelector(".member-name-dashboard").textContent =
      member.memberName;
    document.querySelector(".member-username-dashboard").textContent =
      member.memberUsername;
    document.querySelector(".member-email").value = member.memberEmail;
    document.querySelector(".member-password").value = member.memberPassword;
    document.querySelector(".member-intro-text").value = member.memberIntro;
  });

// UPLOAD FILE //
const submitBtn = document.querySelector(".submit-btn");

submitBtn.addEventListener("click", function () {
  const memberName = document.querySelector(".member-name").value;
  const memberUsername = document.querySelector(".member-username").value;
  const memberEmail = document.querySelector(".member-email").value;
  const memberPassword = document.querySelector(".member-password").value;
  const memberIntro = document.querySelector(".member-intro-text").value;
  const file = hiddenInput.files[0];
  const fileReader = new FileReader();

  fileReader.onload = function (e) {
    const base64str = btoa(e.target.result);
    fetch("memberUpdate", {
      method: "POST",
      headers: {
        // we're telling Java what kind of file we're sending
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        picStr: base64str,
        memberName,
        memberUsername,
        memberEmail,
        memberPassword,
        memberIntro,
      }),
    });
  };
  fileReader.readAsBinaryString(file);
});

// $('.member-email input[name=my_id]').val(row.my_id).prop('readonly', true);
