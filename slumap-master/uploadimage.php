<?php
$target_dir = "poi/";
$target_file = $target_dir . basename($_FILES["fileToUpload"]["name"]);
$uploadOk = 1;
$imageFileType = strtolower(pathinfo($target_file,PATHINFO_EXTENSION));

// checks if image uploaded was an image or nah
if(isset($_POST["submit"])) {
    $check = getimagesize($_FILES["fileToUpload"]["tmp_name"]);
    if($check !== false) {
        echo "File is an image - " . $check["mime"] . ".";
        $uploadOk = 1;
    } else {
        echo "File is not an image.";
        $uploadOk = 0;
    }
}

// checks if poi already has an image
if (file_exists($target_file)) {
    echo "There is already an existing image!";
    $uploadOk = 0;
}

// checks file size
if ($_FILES["fileToUpload"]["size"] > 500000) {
    echo "The file uploaded is too large.";
    $uploadOk = 0;
}

// allows certain file formats
if ($imageFileType != "jpg" && $imageFileType != "png" && $imageFileType != "jpeg" && $imageFileType != "gif") {
    echo "You can only upload JPG, PNG, & GIF files.";
    $uploadOk = 0;
}

// checks if file was uploaded
if ($uploadOk == 0) {
    echo "The file wasn't uploaded.";
} else {
    if (move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file)) {
        echo "Your file ". basename( $_FILES["fileToUpload"]["name"]). " successfully uploaded!";
    } else {
        echo "There was an error uploading the file.";
    }
}
?>
