
var store = {
  setup () { },

  // current user
  isLogin: false,
  role : "",

  userId  : 123123,
  username: "",
  password: "",

  login (callback) {
    setTimeout(
      () => {
        store.isLogin = true
        store.role  = "seller"
        store.username = "Shin"
        callback && callback()
      },
      0
    )
  },

  logout () {
    setTimeout(
      () => {
        store.isLogin = false
        store.role  = ""
      },
      0
    )
  }
  
}

export { store }
