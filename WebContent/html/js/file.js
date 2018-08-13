 
//fileEleId type为file的控件id			showId是要显示的img id    resultId是隐藏的id用户数据库存值
function uploadImg(fileEleId,showId,resultId){
	$.ajaxFileUpload({
		url : admin_api + "file/upload/photo.json",
		type : "post",
		fileElementId : fileEleId,
		dataType : "json",
		//data : {width:100, height:100}, 
		beforeSend : function() {
		},
		success : function(result) {
			if (result.resultCode == 1000) {
				alert("上传成功!");
				document.getElementById(showId).src = result.data;
				$('#'+resultId).val(result.data);
			}else {
				alert(result.error);
			}
		},
		error : function(msg) {
			alert("上传失败!" + msg);
		}
	});
};

//上传图 并展示下一个   parentId父级id
function uploadImg(fileEleId,showId,resultId,parentId){
	//获取当前多少个
	var nowsize = document.getElementById(parentId).getElementsByTagName("div").length;
	$.ajaxFileUpload({
		url : admin_api + "file/upload/photo.json",
		type : "post",
		fileElementId : fileEleId,
		dataType : "json",
		//data : {width:100, height:100}, 
		beforeSend : function() {
		},
		success : function(result) {
			if (result.resultCode == 1000) {
				alert("上传成功!");
				document.getElementById(showId).src = result.data;
				$('#'+resultId).val(result.data);
				//拼接
				//限制为9张图
				if(nowsize >=9)
					return;
				var str = "";
				if(nowsize % 3 !=0)
					str = '<div id="preview1" style="float: left; width: 30%; height: 148px; margin: 0;margin-left: 18px;';
				else if(nowsize % 3 ==0)
					str = '<div id="preview1" style="float: left; width:30%; height: 148px; margin: 0;margin-top: 10px;';
				
				if(nowsize>3)
					str+= 'margin-top: 10px;">';
				else{
					str+= '">';
				}
				str+='<img id="imghead' +nowsize+1 + '"  border="0" src="'+ path +'/newassets/images/LOGO_03.jpg" style="width:100%; height: 148px;"  onClick="document.getElementById(\'adv'+(nowsize+1)+'\').click()">';
				str+='<input type="hidden" id="img'+(nowsize+1)+'" />';
				str+='<input type="file" name="photo" class="preview1Img" id="adv'+(nowsize+1)+'" onchange="uploadImg('+'\''+'adv'+(nowsize+1)+'\',\'imghead'+(nowsize+1)+'\',\'img'+(nowsize+1)+'\',\''+parentId+'\')">';
				str+='</div>';
				alert(str);
				$("#"+parentId).append(str);
			}else {
				alert(result.error);
			}
		},
		error : function(msg) {
			alert("上传失败!" + msg);
		}
	});
};




function preview1Image(file)
        {
          var MAXWIDTH  = 90; 
          var MAXHEIGHT = 90;
          var div = document.getElementById('preview1');
          if (file.files && file.files[0])
          {
              div.innerHTML ='<img id=imghead1 onclick=$("#preview1Img").click()>';
              var img = document.getElementById('imghead1');
              img.onload = function(){
                var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
                img.width  =  rect.width;
                img.height =  rect.height;
//                 img.style.marginLeft = rect.left+'px';
                img.style.marginTop = rect.top+'px';
              }
              var reader = new FileReader();
              reader.onload = function(evt){img.src = evt.target.result;}
              reader.readAsDataURL(file.files[0]);
          }
          else //兼容IE
          {
            var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="';
            file.select();
            var src = document.selection.createRange().text;
            div.innerHTML = '<img id=imghead1>';
            var img = document.getElementById('imghead1');
            img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src;
            var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight);
            status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height);
            div.innerHTML = "<div id=divhead style='width:"+rect.width+"px;height:"+rect.height+"px;margin-top:"+rect.top+"px;"+sFilter+src+"\"'></div>";
          }
        }
        function clacImgZoomParam( maxWidth, maxHeight, width, height ){
            var param = {top:0, left:0, width:width, height:height};
            if( width>maxWidth || height>maxHeight ){
                rateWidth = width / maxWidth;
                rateHeight = height / maxHeight;
                
                if( rateWidth > rateHeight ){
                    param.width =  maxWidth;
                    param.height = Math.round(height / rateWidth);
                }else{
                    param.width = Math.round(width / rateHeight);
                    param.height = maxHeight;
                }
            }
            param.left = Math.round((maxWidth - param.width) / 2);
            param.top = Math.round((maxHeight - param.height) / 2);
            return param;
        }
	  