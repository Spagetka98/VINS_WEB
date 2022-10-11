import React, { useState, useEffect } from "react";
import styled from "styled-components";
import tw from "twin.macro";
import moment from "moment";
import ClockLoader from "react-spinners/ClockLoader";

import Client from "../../config/axios/client";
import { GET_TOY_VOICE_ACTIVATION_INFORMATION } from "../../config/axios/endpoints";

const VoicePanel = ({ token }) => {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(false);

  useEffect(() => {
    setLoading(true);

    Client.get(GET_TOY_VOICE_ACTIVATION_INFORMATION, {
      params: {
        token: token,
      },
    })
      .then((response) => {
        setData(response?.data);
        console.log(response);
      })
      .catch((error) => {})
      .finally(() => {
        setLoading(false);
      });
  }, [token]);

  return loading ? (
    <SpinnerBox>
      <ClockLoader color="#ffb600" loading={true} />
    </SpinnerBox>
  ) : (
    <Panel>
      <TitleBar>
        <Col>
          <Title>Dnešní zvukové záznamy</Title>
          <Title>{data?.data?.length}</Title>
        </Col>
      </TitleBar>
      {data?.data?.map((dataPiece, index) => (
        <TimeLine key={index}>
          <TimeLineBox>
            <Circle />
            {index < data?.data?.length - 1 && <Line />}
          </TimeLineBox>

          <TimeLineTitle>
            {moment(dataPiece?.time).format("DD.MM.yyyy HH:mm:ss")}-
            {dataPiece?.voiceData}
          </TimeLineTitle>
        </TimeLine>
      ))}
    </Panel>
  );
};

const Panel = styled.div`
  ${tw`w-full flex flex-col my-4`}
`;

const TitleBar = styled.div`
  ${tw`w-full flex justify-around mb-10`}
`;

const Col = styled.div`
  ${tw`flex flex-col items-center`}
`;

const TimeLineBox = styled.div`
  ${tw`flex flex-col items-center `}
`;

const Circle = styled.div`
  ${tw`w-8 h-8 bg-gray-400 rounded-full`}
`;

const Line = styled.div`
  ${tw`w-1 h-20 bg-gray-200 `}
`;

const TimeLine = styled.div`
  ${tw`flex justify-center`}
`;

const Title = styled.span`
  ${tw`flex text-base lg:text-lg xl:text-xl 2xl:text-2xl font-extralight italic`}
`;

const TimeLineTitle = styled.span`
  ${tw`flex text-base xl:text-lg 2xl:text-xl font-extralight ml-4 italic`}
`;

const SpinnerBox = styled.div`
  ${tw`flex justify-center items-center`}
`;
export default VoicePanel;
