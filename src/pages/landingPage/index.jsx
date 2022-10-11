import React from "react";
import styled from "styled-components";
import tw from "twin.macro";

import Landing from "../../components/landing";

const LandingPage = () => {
  return (
    <Body>
      <Landing />
    </Body>
  );
};

const Body = styled.main`
  ${tw`h-screen`}
`;

export default LandingPage;
