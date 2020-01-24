package com.android.kotlindemo.utils

/**
 * Created by devandro on 12/21/17.
 */
object RequestCodes {
    const val CALL_PHONE = 1
    const val ALL_PERMISSION = 2
    const val IN_TRIP_ISSUE = 3
    const val RC_LOC_PERM = 4
    const val ChangeContactCallApi = 5
    const val ChangePasswordApi = 6
    const val RESULT_LOAD_IMAGE = 7
    const val RC_STORAGE_PERM = 8
    const val UpdateProfileApi = 9
    const val WRITE_PERMISSION = 10
    const val PICK_IMAGE_CAMERA = 11
    const val PICK_IMAGE_GALLERY = 12
    const val RATING_REASON = 12345
    const val VERIFY_CODE = 1
    const val RESEND_CODE = 2
    const val TLC = 3
    const val DMV = 4
    const val ADDRESS = 5
    const val SSN = 6
    const val CREDIT_CARD = 7
    const val ACCIDENT = 9
    const val DUI = 10
    const val UPLOAD_VIDEO = 11
    const val SIGNATURE = 12
    const val SEARCH_VEHICLE = 13

    object API {
        const val APPROVAL_REQUEST = 11
        const val LOGIN = 1
        const val PROFILE = 2
        const val RENTAL_PACKAGES_LIST = 3
        const val GET_PAYMENT_METHODS = 4
        const val MAKE_CARD_DEFAULT = 5
        const val DELETE_CARD = 6
        const val PURCHASE_PACKAGE = 7
        const val GET_CUSTOMER_PACKAGES = 8
        const val ADD_PROFILE_PIC = 9
        const val CHANGE_PASSWORD = 10
        const val GET_DOCUMENTS = 11
        const val GET_CUSTOMER_RENTALS = 12
        const val GET_PACKAGE_RENTALS = 13
        const val GET_VEHICLE_TYPES = 3
        const val GET_NEARBY_VEHICLES = 4
        const val SET_REMINDER = 5
        const val GET_RENTAL_DETAIL = 6
        const val GET_SELECTED_VEHICLE = 7
        const val GET_CAB_SERVICE = 8
        const val CREATE_RESERVATION = 9
        const val UPLOAD_START_VIDEO = 10
        const val UPLOAD_END_VIDEO = 11
        const val CANCEL_RESERVATION = 12
        const val START_RENTAL = 13
        const val ACTIVE_RESERVATION = 14
        const val END_RENTAL = 15
        const val ACTIVE_RENTAL = 16
        const val ADD_DEVICE = 17
        const val LOGOUT = 18
        const val MOCK_TOKEN = 19
        const val RATING_RESONS = 20
        const val SUBMIT_RATING = 21
        const val ACTIVE_PROMO = 22
        const val GET_ACTIVE_PROMOTIONS = 23
        const val GET_REFFERAL_TEXT = 24
    }

    object INTENT {
        const val POLICE_REPORT_EVIDENCE = 8
    }
}