/**
 * 
 */
 
 /** 초기 설정 1. 가입날짜
  */
  var profile_joinDate = document.querySelector('.profile__joinDate');
  var joinDate = profile_joinDate.innerText;
  joinDate = setJoinDate(joinDate);
  profile_joinDate.innerText = joinDate;
 
 /** 초기 설정 2. 받은 평가
  */
  const avgRates = document.querySelectorAll('.avg-rate');
  for(avgRate of avgRates){
		setRateInStar(avgRate);
	}
  
  const rates = document.querySelectorAll('.rate');
  for(rate of rates){
		setRateInStar(rate);
	}
 
 /** const btnAvatar
	* 1. 클릭하면 file 팝업을 띄운다.
	* 2. input file이 변경되면 form을 제출한다.
  */
  
  const btnAvatar = document.querySelector('.profile__photo');
	const inputFile = document.querySelector('.input-file');
  const profileForm = document.querySelector('.profile__form');
  
  btnAvatar.addEventListener('click', ()=>{
		inputFile.click();
		inputFile.addEventListener('change', ()=>{
			profileForm.submit();
		});
});

/** 초기설정 3. 가격 표시
 */
const infoPrices = document.querySelectorAll('.info__price');
for(infoPrice of infoPrices){
	setPrice(infoPrice);
	if(infoPrice.innerText == '0 원' && infoPrice.previousElementSibling.innerText != 14){
		infoPrice.innerText = '무료나눔';
	}
}

/** 초기설정 4. 선택한 페이지와 조회조건(전체/판매자/구매자)
 */
const pageContainer = document.querySelector('.page-container');
selectPage(pageContainer);
const typeContainer = document.querySelector('.type-container');
const typeVal = document.querySelector('.type-value');
if(typeContainer != null){
	if(typeVal.innerText == ""){
		typeVal.innerText = 0;
	}
	let selectedType = typeContainer.querySelector('.code_'+typeVal.innerText);
	selectedType.classList.add('selected');	
}


/* products */

function goSelect(productNum){
	location.href = '/product/select/'+productNum;
}