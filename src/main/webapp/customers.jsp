<!DOCTYPE html>
<%@page import="com.cubicit.controller.Biz"%>
<%@page import="java.util.List"%>
<html lang="en">
<head>
  <title>Customer Ajax!!!</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
  
  <script type="text/javascript">
  
    function sendData(){
    	  var pname=$("#pname").html();
		  var pemail=$("#pemail").html();
		  var cdata={name:pname,email:pemail};
		   //convert above object into JSON
   	     var jsonData=JSON.stringify(cdata);
   	  
   	   //Creating 
   	   const options = {
                  method: 'POST',
                  body: jsonData,
                  headers: {
                    'Content-Type': 'application/json',
                    'Accept': 'application/json'
                  }
                };
   	   
   	   var promise=fetch("v3/ccustomer",options);
   	   promise.then(response=>response.json())
   	   .then(function(data){
   		   console.log(data);
   	   });
    	
    }
  
     function generateCard(){
    	  var name=$("#tpname").val();
		  var cid =$("#tpcid").val();
		  // <img src="img/credit-card-front-template.jpg" id="cphoto">
		  var tete=new Date();
		  $("#cphoto").attr("src","loadCrediCard?cid="+cid+"&name="+name+"&time="+tete);
     }
  
     function openPopup(pcid){
    	 //	@GetMapping(value="/customers",params={"cid"})
    	 fetch("v3/customers?cid="+pcid).then(function(response){
			return response.json();//return json data to next fucntion
		}).then(function(data){ //data is now holding JavaScript object
			console.log(data);
		    //{"cid":3,"name":"Michal","email":"michal@gmail.com","debitcard":"234204329842384","valid":"09/2022","cvv":122,"mobile":"+19282782722","photo":null,"dbphoto":null,"age":32,"company":"WIPRO"}
            //<span style="font-size: 18px" id="pname"></span>
		    $("#pname").html("Name : "+data.name);
		    $("#pemail").html("Email : "+data.email);
		  
		    //setting these values inside hidden field
		    $("#tpname").val(data.name);
		    $("#tpcid").val(pcid);
		    
		    $("#crediCardPopup").modal("show");  		    
		});
     }
  
     function loadCustomer(){
		//I have to write code to fetch data from the server using AJAX and data will come from the server
		//in which format? json
		//http://localhost:444/cubic-mvc/v3/customers = this is called endpoint
		
		//method -GET
		//AJAX call
		//var promise=window.fetch("v3/customers");
		fetch("v3/customers").then(function(response){
			return response.json();
		}).then(function(data){ //data is java script object which holds json data coming from server
		    //data is array of javascript
		    //
		    var pk=[];
		    pk.push("Nagendra");
		    //{"cid":3,"name":"Michal","email":"michal@gmail.com","debitcard":"234204329842384","valid":"09/2022","cvv":122,"mobile":"+19282782722","photo":null,"dbphoto":null,"age":32,"company":"WIPRO"}
		    var tdata="";
			data.forEach((pdata)=> {
				console.log(pdata);
				tdata=tdata+'<tr>';
				tdata=tdata+'<td>'+pdata.cid+'</td>';
				tdata=tdata+'<td>'+pdata.name+'</td>';
				tdata=tdata+'<td>'+pdata.email+'</td>';
				tdata=tdata+'<td>'+pdata.debitcard+'</td>';
				tdata=tdata+'<td>'+pdata.valid+'</td>';
				tdata=tdata+'<td>'+pdata.cvv+'</td>';
				tdata=tdata+'<td>'+pdata.mobile+'</td>';
				tdata=tdata+'<td>coming soon</td>';
				tdata=tdata+'<td>'+pdata.company+'</td>';
				tdata=tdata+'<td> <button type="button" class="btn btn-primary" onclick="openPopup('+pdata.cid+');">GEN</button></td>';
				tdata=tdata+'</tr>';
			});
			document.getElementById("cdata").innerHTML=tdata;
		});
     }
  
  </script>
  
</head>
<body>

<header style="height: 30px;background-color: maroon;">
</header>

<div class="container">
   <img src="img/header.png">
   <hr/>
   <span style="font-size: 18;color: black;font-weight: bold;">
   Customer Data
    <button type="button" class="btn btn-danger" onclick="loadCustomer();">Load Customers</button>
   ${message}
   </span>
   <hr/>
   <div style="width: 100%">
   
   <table class="table table-bordered">
    <thead>
      <tr style="background-color: #50aaff;">
       <th>Cid</th>
        <th>Name</th>
        <th>Email</th>
        <th>Debit Card</th>
        <th>Valid</th>
        <th>CVV</th>
        <th>Mobile</th>
        <th>Photo</th>
        <th>Company</th>
        <th>Action</th>
      </tr>
    </thead>
    <tbody id="cdata">
    
    </tbody>
  </table>
   
  </div>
</div>




 <!-- The Modal -->
  <div class="modal" id="crediCardPopup">
    <div class="modal-dialog">
      <div class="modal-content" style="width: 540px;">
      
       <input type="hidden" id="tpname">
       <input type="hidden" id="tpcid">
      
        <!-- Modal Header -->
        <div class="modal-header">
          <h4 class="modal-title">Customer Credit Card!</h4>
          <button type="button" class="close" data-dismiss="modal">&times;</button>
        </div>
        
        <!-- Modal body -->
        <div class="modal-body">
             <span style="font-size: 18pxlfont-weight: bold;" id="pname" ></span>
             <span style="font-size: 18px;color: red;font-weight: bold;" id="pemail"></span>
             <hr/>
             <img src="img/credit-card-front-template.jpg" id="cphoto">
        </div>
        
        <!-- Modal footer -->
        <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="generateCard()">Generate Card</button>
          <button type="button" class="btn btn-warning" onclick="sendData();">Save</button>
        </div>
        
      </div>
    </div>
  </div>

</body>
</html>
