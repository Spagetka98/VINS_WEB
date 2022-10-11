import React from "react";
import styled from "styled-components";
import tw from "twin.macro";
import { useParams, Navigate } from "react-router-dom";

import InformationPanel from "../../components/informationPanel";

const DashBoardPage = () => {
  let { token } = useParams();

  return (
    <Body>
      {token ? (
        <InformationPanel token={token} />
      ) : (
        <Navigate to="/" replace={true} />
      )}
    </Body>
  );
};

const Body = styled.main`
  ${tw`h-screen`}
`;
export default DashBoardPage;
