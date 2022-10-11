import React from "react";
import styled from "styled-components";
import tw from "twin.macro";

import Cat from "../../assets/cat/cat.jpg";
import CounterPanel from "../counterPanel";
import VoicePanel from "../voicePanel";

const InformationPanel = ({ token }) => {
  return (
    <Body>
      <Box>
        <Img src={Cat} alt="Logo" />
        <Title>Emilek</Title>
        <Content>
          <Panel>
            <CounterPanel token={token} />
            <VoicePanel token={token} />
          </Panel>
        </Content>
      </Box>
    </Body>
  );
};

const Body = styled.div`
  ${tw`flex min-h-full flex-col bg-gray-100 items-center`}
`;

const Box = styled.div`
  ${tw`flex w-[85%] flex-col bg-white border-4 border-black m-8 rounded-2xl shadow-2xl`}
`;

const Img = styled.img`
  ${tw` w-[200px] h-[200px] object-cover rounded-full border-8 border-pink-500 align-self[center] my-2 shadow-2xl`}
`;

const Title = styled.span`
  ${tw`align-self[center] text-base sm:text-lg md:text-xl lg:text-2xl xl:text-3xl 2xl:text-4xl font-extralight italic`}
`;

const Content = styled.div`
  ${tw`w-full border-t-2 border-black`}
`;

const Panel = styled.div`
  ${tw`w-full flex flex-col lg:flex-row `}
`;

export default InformationPanel;
