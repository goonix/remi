class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

		"/"(view:"/index")
		"500"(view:'/error')
	}

    /*
    //force RESTful services
    static mappings = {
          "/$controller/$id?"{
                  action = [GET:"show", POST:"save", PUT:"update", DELETE:"remove"]
                    }
      "500"(view:'/error')
    }
    */
}
