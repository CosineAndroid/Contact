package kr.camp.contact.registry

import kr.camp.contact.R
import kr.camp.contact.data.Contact

object ContactRegistry {

    private val _contacts = mutableListOf<Contact>(
        Contact(
            profileImageDrawableId = R.drawable.ms,
            name = "Microsoft",
            phoneNumber = "1577-9700",
            website = "https://www.microsoft.com/ko-kr",
            memo = "미국에 위치한 대규모 IT 기업이다. 오피스와 윈도우를 통해 일반 소비자들을 상대로도 매우 높은 인지도를 갖추고 있다"
        ),
        Contact(
            profileImageDrawableId = R.drawable.apple,
            name = "Apple",
            phoneNumber = "080–330–8877",
            website = "https://www.apple.com/kr/",
            memo = "Apple은 1976년 4월 1일에 설립된 미국의 전자제품 제조사로, 세부 업종은 하드웨어, 소프트웨어, 온라인 서비스 등의 디자인·설계, 개발, 제조·제작[9] 및 판매업이다."
        ),
        Contact(
            profileImageDrawableId = R.drawable.google,
            name = "Google",
            phoneNumber = "080-234-0051",
            website = "https://www.google.co.kr/?hl=ko",
            memo = "구글은 알파벳 산하의 종합 IT 기업으로, 역사상 전 세계 최대의 인터넷 기업중 하나이다."
        ),
        Contact(
            profileImageDrawableId = R.drawable.amazon,
            name = "Amazon",
            phoneNumber = "1-800-280-4331",
            website = "https://www.amazon.com/",
            memo = "아마존닷컴은 미국의 종합 인터넷 플랫폼이다. 세계 최대 규모의 온라인 쇼핑몰과 클라우드 컴퓨팅 서비스를 제공하고 있다."
        ),
        Contact(
            profileImageDrawableId = R.drawable.meta,
            name = "Meta",
            phoneNumber = "02-737-0455",
            website = "https://www.meta.com/kr/",
            memo = "메타는 미국의 종합 IT 기업이다. 현재 대표이사인 페이스북 창업자 마크 저커버그의 주도로 그를 포함해서 다섯 명이 창업했다"
        ),
        Contact(
            profileImageDrawableId = R.drawable.tesla,
            name = "Tesla",
            phoneNumber = "080-617-1399",
            website = "https://www.tesla.com/ko_kr",
            memo = "테슬라는 미국의 전기자동차 제조업체이다."
        )
    )
    val contacts: List<Contact> get() = _contacts

    fun addContact(contact: Contact) {
        _contacts.add(contact)
    }
}