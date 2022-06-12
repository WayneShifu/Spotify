package com.world.wayne.spotify.auth

import com.world.wayne.spotify.auth.Auth.getAndStoreClientIdClientSecret
import org.scalatest.FlatSpec


class AuthTest extends FlatSpec {

  it must "thrown error about Environment Variable Not Set" in {
    val thrown = intercept[Exception] {
      getAndStoreClientIdClientSecret("dummy1", "dummy2", key => sys.env(key))
    }
    assert(thrown.getMessage.contains("Please set Environment Variables"))
  }

  it must "throw error about invalid_client" in {
    val thrown = intercept[Exception] {
      Auth("clientId", "clientSecret")
    }
    assert(thrown.getMessage.contains("invalid_client"))
  }
}
